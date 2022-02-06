package com.gcc.oa.dto.request;

import com.gcc.oa.dto.PageDTO;

import java.util.List;


/**
 * 员工与角色关系表(EmployeeRole)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 22:47:40
 */
public class EmployeeRoleReqDTO extends PageDTO {
    private static final long serialVersionUID = -8282781850509027461L;
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

    /**
     * 角色ID集合
     */
    private List<Integer> roleIds;


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

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "EmployeeRoleReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", emRoleId=" + emRoleId +
                ", emId=" + emId +
                ", roleId=" + roleId +
                ", roleIds=" + roleIds +
                '}';
    }
}

