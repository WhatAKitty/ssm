package com.sccbv.system.user;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体类
 *
 * @date 2018/04/12
 * @description
 **/
@Data
@Table(name = "auth_user")
@EqualsAndHashCode(callSuper = true)
public class User extends SDelEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否过期
     */
    private Boolean isExpired;

    /**
     * 是否被锁定
     */
    private Boolean isLocked;

    /**
     * 是否启用
     */
    private Boolean isEnabled;


}
