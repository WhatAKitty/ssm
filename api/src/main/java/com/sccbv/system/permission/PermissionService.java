package com.sccbv.system.permission;

import com.sccbv.system.rolespermissions.RolesPermissionsDTO;
import com.sccbv.system.rolespermissions.RolesPermissionsService;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.service.BusinessService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 系统权限业务层
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Service
public class PermissionService extends BusinessService<Permission, PermissionDTO> {

    @Autowired
    private RolesPermissionsService rolesPermissionsService;

    @Autowired
    public PermissionService(PermissionMapper mapper) {
        super(mapper, Permission.class);
    }

    /**
     * 判断数据是否重复
     *
     * @param permissionDTO 权限数据
     * @param permissionId  权限编号
     */
    public void exist(PermissionDTO permissionDTO, Long permissionId) {
        Example example = Example.builder(Permission.class).build();

        example.and()
            .orEqualTo("code", permissionDTO.getCode())
            .orEqualTo("name", permissionDTO.getName());

        if (permissionId != null) {
            example.and().andNotEqualTo("id", permissionId);
        }

        Asserts.isFalse(super.existsByExample(example, false), 400, "权限名称或权限代码已存在");
    }

    /**
     * 创建权限
     *
     * @param permissionDTO 权限数据
     * @return 权限数据
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Permission create(PermissionDTO permissionDTO) {
        exist(permissionDTO, null);
        return super.create(permissionDTO, new Date());
    }

    /**
     * 修改权限
     *
     * @param permissionDTO 权限数据
     * @param permissionId  权限编号
     * @return 权限数据
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Permission update(PermissionDTO permissionDTO, Long permissionId) {
        exist(permissionDTO, permissionId);
        return super.update(permissionId, permissionDTO, false, new Date());
    }

    /**
     * 删除权限
     *
     * @param permissionId 权限编号
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    @Override
    public Permission destroy(Long permissionId) {
        RolesPermissionsDTO rolesPermissionsDTO = new RolesPermissionsDTO();
        rolesPermissionsDTO.setPermissionId(permissionId);

        Asserts.isFalse(
            rolesPermissionsService.existsByDTO(rolesPermissionsDTO, false),
            400,
            "权限已关联角色，无法删除"
        );

        return super.destroy(permissionId, false);
    }


}
