package com.sccbv.system.menu;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 菜单数据传输载体
 *
 * @date 2018/04/08
 * @description
 **/
@Data
public class MenuDTO {

    private Long id;

    @NotEmpty(
        groups = {MenuCreateGroup.class, MenuUpdateGroup.class},
        message = "{error.message.menu.title.empty}"
    )
    private String title;

    private Long parent;

    private String spel;

    private String icon;

    private String url;


    /**
     * 菜单创建验证组
     */
    public interface MenuCreateGroup {
    }

    /**
     * 菜单更新验证组
     */
    public interface MenuUpdateGroup {
    }

}
