package com.gcc.oa.dto.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @className: EmployeeDto
 * @author: 小李探花
 * @date: 2022/1/18 12:30
 * @description: 员工信息响应的对象
 */
@ApiModel(value = "响应请求的数据传输对象", description = "专门用于响应员工请求的参数")
public class EmployeeRespDTO implements Serializable {
    private static final long serialVersionUID = -2354677864138981348L;
    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 职位id
     */
    private Integer positionId;
    /**
     * 状态(0:离职 1:在职)
     */
    private Integer status;
    /**
     * 所属部门
     */
    private DepartmentRespDTO department;
    /**
     * 所在的职位
     */
    private PositionRespDTO position;
    /**
     * 状态说明(0:离职 1:在职)
     */
    private String statusDesc;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PositionRespDTO getPosition() {
        return position;
    }

    public void setPosition(PositionRespDTO position) {
        this.position = position;
    }

    public DepartmentRespDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentRespDTO department) {
        this.department = department;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "EmployeeRespDTO{" +
                "emId=" + emId +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", depId=" + depId +
                ", positionId=" + positionId +
                ", status=" + status +
                ", department=" + department +
                ", position=" + position +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
