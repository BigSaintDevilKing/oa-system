package com.gcc.oa.dto.request;

import com.gcc.oa.dto.PageDTO;

import java.util.List;


/**
 * 角色与权限关系表(RolePermission)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 23:16:07
 */
public class RolePermissionReqDTO extends PageDTO {
    private static final long serialVersionUID = -3429599739754775318L;
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
    /**
     * 权限ID集合
     */
    private List<Integer> permissionIds;


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

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RolePermissionReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", rolePermissionId=" + rolePermissionId +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", permissionIds=" + permissionIds +
                '}';
    }
}

