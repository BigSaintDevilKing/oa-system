package com.gcc.oa.dto.response;

import java.io.Serializable;

/**
 * 员工与角色关系表(EmployeeRole)实体类
 *
 * @author 小李探花
 * @since 2022-01-31 22:47:40
 */
public class EmployeeRoleRespDTO implements Serializable {
    private static final long serialVersionUID = -5288720467281463132L;
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
     * 状态(0:无效 1:有效)
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 角色信息
     */
    private RoleRespDTO roleRespDTO;


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

    public RoleRespDTO getRoleRespDTO() {
        return roleRespDTO;
    }

    public void setRoleRespDTO(RoleRespDTO roleRespDTO) {
        this.roleRespDTO = roleRespDTO;
    }

    @Override
    public String toString() {
        return "EmployeeRoleRespDTO{" +
                "emRoleId=" + emRoleId +
                ", emId=" + emId +
                ", roleId=" + roleId +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", roleRespDTO=" + roleRespDTO +
                '}';
    }
}

