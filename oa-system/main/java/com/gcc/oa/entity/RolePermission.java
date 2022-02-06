package com.gcc.oa.entity;

import java.io.Serializable;

/**
 * 角色与权限关系表(RolePermission)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 23:16:07
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -11481471249631094L;
    /**
     * 角色与权限关系id
     */
    private Integer rolePermissionId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;


    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}

