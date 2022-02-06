package com.gcc.oa.dto.request;

import com.gcc.oa.dto.PageDTO;


/**
 * @className: RoleReqDTO
 * @author: 小李探花
 * @date: 2022/1/30 21:24
 * @description:
 */
public class RoleReqDTO extends PageDTO {
    private static final long serialVersionUID = 318988360566935286L;
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
     * 描述角色
     */
    private String roleDesc;


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

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "RoleReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
