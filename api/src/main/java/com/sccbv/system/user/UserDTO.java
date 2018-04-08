package com.sccbv.system.user;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统用户数据载体类
 *
 * @author yuhailun
 * @date 2018/01/12
 * @description
 **/
@Data
@Builder
public class UserDTO {

    private Long id;

    @NotEmpty(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.username.empty}"
    )
    private String username;

    @NotEmpty(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.password.empty}"
    )
    private String password;

    @NotEmpty(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.name.empty}"
    )
    private String name;

    private Long schoolId;

    private Long majorId;

    @NotNull(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.isExpired.null}"
    )
    private Boolean isExpired;

    @NotNull(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.isLocked.null}"
    )
    private Boolean isLocked;

    @NotNull(
        groups = {UserCreateGroup.class},
        message = "{error.message.user.isEnabled.null}"
    )
    private Boolean isEnabled;

    /**
     * @date 2018/01/12
     */
    public interface UserCreateGroup {
    }

    /**
     * @date 2018/01/12
     */
    public interface UserUpdateGroup {
    }

}
