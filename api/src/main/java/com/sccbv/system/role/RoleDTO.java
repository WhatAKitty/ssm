package com.sccbv.system.role;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 角色传输数据载体
 *
 * @author xuqiang
 * @date 2018/01/09
 * @description
 **/
@Data
public class RoleDTO {

    private Long id;

    @NotEmpty(
        groups = {RoleCreateGroup.class},
        message = "{error.message.role.name.empty}"
    )
    private String name;

    @NotEmpty(
        groups = {RoleCreateGroup.class},
        message = "{error.message.role.code.empty}"
    )
    private String code;

    private String remark;


    /**
     * 系统角色创建验证组
     *
     * @date 2018/1/14
     */
    interface RoleCreateGroup {
    }

    /**
     * 系统角色更新验证组
     *
     * @date 2018/1/14
     */
    interface RoleUpdateGroup {
    }

}
