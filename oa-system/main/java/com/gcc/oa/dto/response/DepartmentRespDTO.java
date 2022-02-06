package com.gcc.oa.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @className: DepartmentRespDTO
 * @author: 小李探花
 * @date: 2022/1/21 14:57
 * @description: 对于部门请求的数据响应包装类
 */
@ApiModel(value = "响应部门请求", description = "对于部门请求的数据响应包装类")
public class DepartmentRespDTO implements Serializable {
    private static final long serialVersionUID = 8521043132682169256L;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门的ID", required = true, example = "1")
    @TableId(type = IdType.AUTO)
    private Integer depId;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门的名称", required = true, example = "研发部")
    private String name;
    /**
     * 办公地点
     */
    @ApiModelProperty(value = "部门的办公地点", required = true, example = "广东省广州市黄埔区合景天峻A11栋")
    private String address;
    /**
     * 1-代表有效, 0-代表无效
     */
    @ApiModelProperty(value = "部门的是否存在 (1-代表有效, 0-代表无效)", required = true, example = "1")
    private Integer status;

    @ApiModelProperty(value = "对部门状态的说明 (1-代表有效, 0-代表无效)", required = true, example = "有效")
    private String statusDesc;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "DepartmentRespDTO{" +
                "depId=" + depId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
