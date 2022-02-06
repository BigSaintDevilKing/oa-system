package com.gcc.oa.dto.request;

import com.gcc.oa.dto.PageDTO;
import com.gcc.oa.entity.Department;
import com.gcc.oa.entity.Position;
import io.swagger.annotations.ApiModel;


/**
 * @className: EmployeeDto
 * @author: 小李探花
 * @date: 2022/1/18 12:30
 * @description: 员工信息接收对象
 */
@ApiModel(value = "请求接收的数据传输对象", description = "专门用于接收员工发起的请求所携带的参数")
public class EmployeeReqDTO extends PageDTO {
    private static final long serialVersionUID = -8851067708572040000L;
    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 密码
     */
    private String password;
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
    private Department department;
    /**
     * 所在的职位
     */
    private Position position;
    /**
     * 验证码
     */
    private String verificationCode;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "EmployeeReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", emId=" + emId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", depId=" + depId +
                ", positionId=" + positionId +
                ", status=" + status +
                ", department=" + department +
                ", position=" + position +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
