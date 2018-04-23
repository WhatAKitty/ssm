package com.sccbv.system.usersroles;

import com.google.common.collect.ImmutableMap;
import com.sccbv.system.role.Role;
import com.sccbv.system.user.User;
import com.sccbv.system.user.UserDTO;
import com.sccbv.system.user.UserService;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.db.mybatis.IdEntity;
import com.whatakitty.ssm.service.BusinessService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户角色关系业务处理层
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Service
public class UsersRolesService extends BusinessService<UsersRoles, UsersRolesDTO> {

    @Autowired
    private UserService userService;

    private final UsersRolesMapper usersRolesMapper;

    @Autowired
    public UsersRolesService(UsersRolesMapper mapper) {
        super(mapper, UsersRoles.class);
        this.usersRolesMapper = mapper;
    }

    /**
     * 通过用户编码查询用户及其角色信息
     *
     * @param userId 用户编码
     * @param force  是否强制
     * @return 用户及其角色信息
     */
    public Map<String, Object> byUserId(Long userId, boolean force) {
        User user = userService.byPrimaryKey(userId, force);

        Asserts.notNull(user, 404, String.format("编号为%s的用户不存在", userId));

        List<Role> roles = usersRolesMapper.byUserId(userId);

        return ImmutableMap.of(
            "user", user,
            "roles", roles
        );
    }

    /**
     * 创建用户角色关系
     *
     * @param usersRolesDTO 用户角色关系数据
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object create(UsersRolesDTO usersRolesDTO) {
        final Date date = new Date();
        final UserDTO userDTO = new UserDTO();

        if (usersRolesDTO.getUserId() == null) {
            BeanUtils.copyProperties(usersRolesDTO, userDTO);

            Long id = getIdGenerate().getNextValue();
            usersRolesDTO.setUserId(id);
            userDTO.setId(id);
            userService.create(userDTO);
        } else {
            Asserts.isTrue(userService.existsByPrimaryKey(
                usersRolesDTO.getUserId(), false),
                404,
                "用户不存在"
            );
        }

        List<Long> roleIdList = usersRolesDTO.getRoleIdList();
        if (roleIdList == null && roleIdList.isEmpty()) {
            return usersRolesDTO;
        }

        List<UsersRolesDTO> usersRolesDTOList = roleIdList
            .parallelStream()
            .map(roleId -> {
                UsersRolesDTO temp = new UsersRolesDTO();
                temp.setUserId(usersRolesDTO.getUserId());
                temp.setRoleId(roleId);
                return temp;
            }).collect(Collectors.toList());

        if (!usersRolesDTOList.isEmpty()) {
            batchCreate(usersRolesDTOList, date);
        }

        return usersRolesDTO;
    }

    /**
     * 修改用户角色关系
     *
     * @param usersRolesDTO 用户角色关系数据
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object update(UsersRolesDTO usersRolesDTO, Long userId) {

        List<Long> roleIdList = usersRolesDTO.getRoleIdList();

        if (roleIdList == null) {
            roleIdList = new ArrayList<>();
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(usersRolesDTO, userDTO);
        userService.update(userId, userDTO);

        Example example = Example.builder(UsersRoles.class).build();
        example.and().andEqualTo("userId", userId);

        List<UsersRoles> usersRolesList = selectByExample(example);

        List<UsersRolesDTO> addList = new ArrayList<>();
        List<UsersRoles> sameList = new ArrayList<>();

        for (Long roleId : roleIdList) {
            boolean isExist = false;
            for (UsersRoles usersRoles : usersRolesList) {
                if (usersRoles.getRoleId().equals(roleId)) {
                    sameList.add(usersRoles);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                UsersRolesDTO temp = new UsersRolesDTO();
                temp.setRoleId(roleId);
                temp.setUserId(userId);
                addList.add(temp);
            }
        }

        if (!usersRolesList.isEmpty()) {
            usersRolesList.removeAll(sameList);
        }

        if (!addList.isEmpty()) {
            super.batchCreate(addList, new Date());
        }

        List<Long> deleteIdList = usersRolesList.stream().map(IdEntity::getId).collect(Collectors.toList());

        if (deleteIdList.isEmpty()) {
            return usersRolesDTO;
        }

        Example deleteExample = Example.builder(UsersRoles.class).build();
        deleteExample.and().andIn("id", deleteIdList);

        Asserts.isTrue(
            usersRolesMapper.deleteByExample(deleteExample) == deleteIdList.size(),
            500,
            "修改失败"
        );
        return usersRolesDTO;
    }


}
