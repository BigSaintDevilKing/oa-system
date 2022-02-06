package com.gcc.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 部门表(Department)实体类
 *
 * @author 小李探花
 * @since 2022-01-18 12:21:19
 */
@TableName("department")
public class Department implements Serializable {
    private static final long serialVersionUID = -49627704081050483L;
    /**
     * 部门id
     */
    @TableId(type = IdType.AUTO)
    private Integer depId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 办公地点
     */
    private String address;
    /**
     * 1-代表有效, 0-代表无效
     */
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

}

