package com.gcc.oa.entity;

import java.io.Serializable;

/**
 * 权限表(Permission)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 377537848928782982L;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 描述权限
     */
    private String permissionDesc;
    /**
     * 状态(0:无效 1:有效)
     */
    private Integer status;
    /**
     * 权限名称
     */
    private String permissionName;


    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

}

