
package com.sccbv.system.permission;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统权限传输载体
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Data
public class PermissionDTO {

    private Long id;

    @NotEmpty(
        groups = {PermissionCreateGroup.class, PermissionUpdateGroup.class},
        message = "{error.message.permission.code.empty}"
    )
    private String code;

    @NotEmpty(
        groups = {PermissionCreateGroup.class, PermissionUpdateGroup.class},
        message = "{error.message.permission.name.empty}"
    )
    private String name;

    @NotEmpty(
        groups = {PermissionCreateGroup.class, PermissionUpdateGroup.class},
        message = "{error.message.permission.category.empty}"
    )
    private String category;

    private String remark;

    /**
     * 权限创建验证组
     *
     * @date 2018/1/14
     */
    interface PermissionCreateGroup {
    }

    /**
     * 权限更新验证组
     *
     * @date 2018/1/14
     */
    interface PermissionUpdateGroup {
    }

}



