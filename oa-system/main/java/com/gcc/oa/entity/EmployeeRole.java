package com.gcc.oa.entity;

import java.io.Serializable;

/**
 * 员工与角色关系表(EmployeeRole)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 23:16:21
 */
public class EmployeeRole implements Serializable {
    private static final long serialVersionUID = 187022159898400301L;
    /**
     * 员工与角色关系id
     */
    private Integer emRoleId;
    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 角色id
     */
    private Integer roleId;


    public Integer getEmRoleId() {
        return emRoleId;
    }

    public void setEmRoleId(Integer emRoleId) {
        this.emRoleId = emRoleId;
    }

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}

