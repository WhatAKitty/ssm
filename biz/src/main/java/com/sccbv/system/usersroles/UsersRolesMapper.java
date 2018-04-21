package com.sccbv.system.usersroles;

import com.sccbv.db.MyMapper;
import com.sccbv.system.role.Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色关系数据操作层
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
public interface UsersRolesMapper extends MyMapper<UsersRoles> {

    /**
     * 根据用户编号查找所属角色列表
     *
     * @param userId 用户编号
     * @return 角色列表
     */
    List<Role> byUserId(@Param("userId") Long userId);

}
