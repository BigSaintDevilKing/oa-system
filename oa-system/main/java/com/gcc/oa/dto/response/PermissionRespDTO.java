package com.gcc.oa.dto.response;

import java.io.Serializable;

/**
 * 权限表(Permission)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
public class PermissionRespDTO implements Serializable {
    private static final long serialVersionUID = 8904948525940559398L;
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

    /**
     * 状态说明
     */
    private String statusDesc;


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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "PermissionRespDTO{" +
                "permissionId=" + permissionId +
                ", permissionDesc='" + permissionDesc + '\'' +
                ", status=" + status +
                ", permissionName='" + permissionName + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}

