package com.sccbv.system.user;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户实体类
 *
 * @author yuhailun
 * @date 2018/01/12
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_user")
public class User extends SDelEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
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

