package com.sccbv.system.rolespermissions;

import com.whatakitty.ssm.db.MyMapper;
import com.sccbv.system.permission.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 角色权限关联表数据库操作类
 *
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/
public interface RolesPermissionsMapper extends MyMapper<RolesPermissions> {

    /**
     * 获取角色关联的权限信息
     *
     * @param roleId 角色编号
     * @return 权限集合
     */
    List<Permission> byRoleId(@Param("roleId") Long roleId);

    /**
     * 获取角色关联的权限信息
     *
     * @param roleIds 角色编号列表
     * @return 权限集合
     */
    List<Permission> byRoleIds(@Param("roleIds") List<Long> roleIds);
}
