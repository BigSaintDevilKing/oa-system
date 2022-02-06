package com.gcc.oa.entity;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author 小李探花
 * @since 2022-01-30 23:34:21
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 418012951718044789L;
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

}

