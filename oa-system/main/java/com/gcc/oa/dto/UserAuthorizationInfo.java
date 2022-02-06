package com.gcc.oa.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @className: UserAuthorizationInfo
 * @author: 小李探花
 * @date: 2022/2/3 13:44
 * @description: 用户角色与权限信息
 */
public class UserAuthorizationInfo implements Serializable {
    private static final long serialVersionUID = -5054414547858191561L;
    /**
     * 用户的角色
     */
    private Set<String> roles;
    /**
     * 用户的权限
     */
    private Set<String> permissions;

    public UserAuthorizationInfo() {
    }

    public UserAuthorizationInfo(Set<String> roles, Set<String> permissions) {
        this.roles = roles;
        this.permissions = permissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserAuthorizationInfo{" +
                "roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
