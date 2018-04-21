package com.sccbv.system.rolespermissions;

import com.whatakitty.ssm.db.mybatis.IdEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关联表类
 *
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_roles_permissions")
public class RolesPermissions extends IdEntity {

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 权限编号
     */
    private Long permissionId;

}
