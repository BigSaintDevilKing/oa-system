package com.gcc.oa.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcc.oa.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @className: DepartmentReqDTO
 * @author: 小李探花
 * @date: 2022/1/21 14:48
 * @description: 接收部门请求参数的对象
 */
@ApiModel(value = "接收部门请求", description = "接收部门请求参数的对象")
public class DepartmentReqDTO extends PageDTO {

    private static final long serialVersionUID = 5945320071697457698L;
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

    @Override
    public String toString() {
        return "DepartmentReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", depId=" + depId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
