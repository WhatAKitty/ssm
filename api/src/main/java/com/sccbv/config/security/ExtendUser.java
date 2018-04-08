package com.sccbv.config.security;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 扩展用户
 *
 * @author xuqiang
 * @date 2018/1/25
 */
@Getter
public class ExtendUser extends org.springframework.security.core.userdetails.User {

    /**
     * 昵称
     */
    private String name;

    /**
     * 扩展用户构造器
     *
     * @param name        名称
     * @param username    用户名
     * @param password    密码
     * @param authorities 授权
     */
    public ExtendUser(
        String name,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.name = name;
    }

    /**
     * 扩展用户构造器
     *
     * @param name                  名称
     * @param username              用户名
     * @param password              密码
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authorities           授权
     */
    public ExtendUser(
        String name,
        String username,
        String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.name = name;
    }

}