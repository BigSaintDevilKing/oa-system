package com.gcc.oa.dto.response;

import java.io.Serializable;

/**
 * @className: RoleReqDTO
 * @author: 小李探花
 * @date: 2022/1/30 21:24
 * @description:
 */
public class RoleRespDTO implements Serializable {
    private static final long serialVersionUID = 9124421287552013661L;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 状态(0:无效 1:有效)
     */
    private Integer status;
    /**
     * 状态的文字说明
     */
    private String statusDesc;
    /**
     * 描述角色
     */
    private String roleDesc;

    /**
     * 角色与权限信息
     */
    private RolePermissionRespDTO rolePermissionRespDTO;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public RolePermissionRespDTO getRolePermissionRespDTO() {
        return rolePermissionRespDTO;
    }

    public void setRolePermissionRespDTO(RolePermissionRespDTO rolePermissionRespDTO) {
        this.rolePermissionRespDTO = rolePermissionRespDTO;
    }

    @Override
    public String toString() {
        return "RoleRespDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", rolePermissionRespDTO=" + rolePermissionRespDTO +
                '}';
    }
}
