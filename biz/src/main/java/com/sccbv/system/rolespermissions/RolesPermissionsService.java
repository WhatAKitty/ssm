package com.sccbv.system.rolespermissions;

import com.google.common.collect.ImmutableMap;
import com.sccbv.system.permission.Permission;
import com.sccbv.system.role.Role;
import com.sccbv.system.role.RoleDTO;
import com.sccbv.system.role.RoleService;
import com.sccbv.system.usersroles.UsersRoles;
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
 * 角色权限关系业务处理层
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Service
public class RolesPermissionsService extends BusinessService<RolesPermissions, RolesPermissionsDTO> {

    @Autowired
    private RoleService roleService;

    private final RolesPermissionsMapper rolesPermissionsMapper;

    @Autowired
    public RolesPermissionsService(RolesPermissionsMapper mapper) {
        super(mapper, RolesPermissions.class);
        this.rolesPermissionsMapper = mapper;
    }

    /**
     * 获取角色及其权限
     *
     * @param roleId 角色编号
     * @return 权限集合
     */
    public Map<String, Object> byRoleId(Long roleId) {
        Role role = roleService.byPrimaryKey(roleId, false);

        Asserts.notNull(role, 400, "编号为%s的角色不存在");

        List<Permission> permissionList = rolesPermissionsMapper.byRoleId(roleId);
        return ImmutableMap.of(
            "role", role,
            "permissions", permissionList
        );
    }

    /**
     * 获取角色及其权限
     *
     * @param roleIds 角色编号列表
     * @return 权限集合
     */
    public List<Permission> byRoleIds(List<Long> roleIds) {
        return rolesPermissionsMapper.byRoleIds(roleIds);
    }

    /**
     * 创建角色权限关系
     *
     * @param rolesPermissionsDTO 角色权限关系数据
     * @return 创建的对象
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object create(RolesPermissionsDTO rolesPermissionsDTO) {
        final Date date = new Date();

        final RoleDTO roleDTO = new RoleDTO();
        if (rolesPermissionsDTO.getRoleId() == null) {
            BeanUtils.copyProperties(rolesPermissionsDTO, roleDTO);

            Long id = getIdGenerate().getNextValue();
            roleDTO.setId(id);
            rolesPermissionsDTO.setRoleId(id);

            roleService.create(roleDTO, date);
        } else {
            Asserts.isTrue(existsByPrimaryKey(rolesPermissionsDTO.getRoleId(), false), 400, "角色不存在");
        }

        List<Long> permissionIdList = rolesPermissionsDTO.getPermissionIdList();
        if (permissionIdList == null || permissionIdList.isEmpty()) {
            return rolesPermissionsDTO;
        }

        List<RolesPermissionsDTO> rolesPermissionsDTOList = permissionIdList.parallelStream().map(permissionId -> {
            RolesPermissionsDTO temp = new RolesPermissionsDTO();
            temp.setRoleId(rolesPermissionsDTO.getRoleId());
            temp.setPermissionId(permissionId);
            return temp;
        }).collect(Collectors.toList());

        if (rolesPermissionsDTOList.isEmpty()) {
            return rolesPermissionsDTO;
        }

        super.batchCreate(rolesPermissionsDTOList, date);
        return rolesPermissionsDTO;
    }

    /**
     * 修改角色权限关系
     *
     * @param rolesPermissionsDTO 角色权限关系数据
     * @param roleId              角色编号
     * @return 更新的对象
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object update(Long roleId, RolesPermissionsDTO rolesPermissionsDTO) {
        List<Long> permissionIdList = rolesPermissionsDTO.getPermissionIdList();

        if (permissionIdList == null) {
            permissionIdList = new ArrayList<>();
        }

        final RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(rolesPermissionsDTO, roleDTO);
        roleService.update(roleId, roleDTO, new Date());

        Example example = Example.builder(RolesPermissions.class).build();
        example.and().andEqualTo("roleId", roleId);

        List<RolesPermissions> rolesPermissionsList = selectByExample(example);

        List<RolesPermissionsDTO> addList = new ArrayList<>();
        List<RolesPermissions> sameList = new ArrayList<>();

        for (Long permissionId : permissionIdList) {
            boolean isExist = false;
            for (RolesPermissions rolesPermissions : rolesPermissionsList) {
                if (rolesPermissions.getPermissionId().equals(permissionId)) {
                    sameList.add(rolesPermissions);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                RolesPermissionsDTO temp = new RolesPermissionsDTO();
                temp.setRoleId(roleId);
                temp.setPermissionId(permissionId);
                addList.add(temp);
            }
        }

        if (!sameList.isEmpty()) {
            rolesPermissionsList.removeAll(sameList);
        }

        if (!addList.isEmpty()) {
            super.batchCreate(addList, new Date());
        }

        List<Long> deleteIdList = rolesPermissionsList.stream().map(IdEntity::getId).collect(Collectors.toList());

        if (deleteIdList.isEmpty()) {
            return rolesPermissionsDTO;
        }

        Example deleteExample = Example.builder(RolesPermissions.class).build();
        deleteExample.and().andIn("id", deleteIdList);

        Asserts.isTrue(
            rolesPermissionsMapper.deleteByExample(deleteExample) == deleteIdList.size(),
            500,
            "修改失败"
        );
        return rolesPermissionsDTO;
    }


}
