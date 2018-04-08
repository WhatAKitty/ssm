package com.sccbv.system.usersroles;

import java.util.List;
import lombok.Data;

/**
 * 用户角色关系数据载体类
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Data
@UsersRolesValidate(message = "创建失败", groups = UsersRolesDTO.UsersRolesCreateGroup.class)
public class UsersRolesDTO {

    private Long userId;

    private String username;

    private String password;

    private String name;

    private Boolean isExpired;

    private Boolean isLocked;

    private Boolean isEnabled;

    private List<Long> roleIdList;

    private Long roleId;

    /**
     * @date 2018/01/24
     */
    public interface UsersRolesCreateGroup {
    }

    /**
     * @date 2018/01/24
     */
    public interface UsersRolesUpdateGroup {
    }


}
