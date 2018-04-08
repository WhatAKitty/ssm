package com.sccbv.system.menu;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单实体类
 *
 * @date 2018/04/08
 * @description
 **/
@Data
@Table(name = "sys_menu")
@EqualsAndHashCode(callSuper = true)
public class Menu extends SDelEntity {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 父菜单
     */
    private Long parent;

    /**
     * 授权判断表达式
     */
    private String spel;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单地址
     */
    private String url;


}
