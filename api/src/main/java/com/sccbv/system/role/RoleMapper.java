package com.sccbv.system.role;

import com.sccbv.config.db.MyMapper;
import com.sccbv.system.permission.Permission;
import java.util.List;

/**
 * 角色数据库操作类
 *
 * @author xuqiang
 * @date 2018/01/09
 * @description
 **/
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 通过角色编号查找所有权限
     *
     * <p>
     * 注意：isDel为null的时候，查找所有
     * </p>
     *
     * @param roleId 角色编号
     * @param isDel  是否已删除
     * @return 权限列表
     */
    List<Permission> permissions(Long roleId, Boolean isDel);

}
