package com.sccbv.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * 详细的权限类
 *
 * @author xuqiang
 * @date 2018/01/25
 * @description
 **/
public class DetailedGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private final String rolePrefix = "ROLE_";

    private final String authority;
    private final String name;
    private final String group;
    private final String remark;
    private final boolean isRole;

    /**
     * 详细的权限信息
     *
     * @param authority 权限编码
     * @param name      权限名称
     */
    public DetailedGrantedAuthority(String authority, String name) {
        this(authority, name, null, null);
    }

    /**
     * 详细的权限信息
     *
     * @param authority 权限编码
     * @param name      权限名称
     * @param group     权限组
     * @param remark    备注
     */
    public DetailedGrantedAuthority(String authority, String name, String group, String remark) {
        Assert.hasText(authority, "A granted authority textual representation is required");
        Assert.hasText(name, "A granted authority name is required");
        this.authority = authority;
        this.name = name;
        this.group = group;
        this.remark = remark;
        this.isRole = authority.startsWith(rolePrefix);
    }

    /**
     * 获取权限编码
     *
     * @return 权限编码
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 获取权限名称
     *
     * @return 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取权限组
     *
     * @return 权限组
     */
    public String getGroup() {
        return group;
    }

    /**
     * 是否为角色的标识
     *
     * @return 是否为角色
     */
    public boolean getIsRole() {
        return isRole;
    }

    /**
     * 是否相等（如果编码和名称一致，则认为相同）
     *
     * @param obj 比较对象
     * @return 是否相同
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SimpleGrantedAuthority) {
            return authority.equals(((DetailedGrantedAuthority) obj).authority)
                && name.equals(((DetailedGrantedAuthority) obj).name);
        }

        return false;
    }

    public int hashCode() {
        return this.authority.hashCode();
    }

    public String toString() {
        return this.authority;
    }

}
