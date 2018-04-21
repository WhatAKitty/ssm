package com.sccbv.system.user;

import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户数据传输载体
 *
 * @date 2018/04/12
 * @description
 **/
@Data
public class UserDTO {

    private Long id;

    @NotEmpty(
        groups = {UserCreateGroup.class, UserUpdateGroup.class},
        message = "{error.message.user.username.empty}"
    )
    private String username;

    @NotEmpty(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.password.empty}"
    )
    private String password;

    @NotEmpty(
        groups = {UserCreateGroup.class, UserUpdateGroup.class},
        message = "{error.message.user.name.empty}"
    )
    private String name;

    private Boolean isExpired;

    private Boolean isLocked;

    private Boolean isEnabled;

    /**
     * 用户所属角色
     */
    private List<Long> roles;


    /**
     * 用户创建验证组
     */
    public interface UserCreateGroup {
    }

    /**
     * 用户更新验证组
     */
    public interface UserUpdateGroup {
    }

}
