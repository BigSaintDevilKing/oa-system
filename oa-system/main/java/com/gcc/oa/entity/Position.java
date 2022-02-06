package com.gcc.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * 职位表(Position)实体类
 *
 * @author 小李探花
 * @since 2022-01-18 12:23:04
 */
@TableName("position")
public class Position implements Serializable {
    private static final long serialVersionUID = -30000183940275443L;
    /**
     * 职位表id
     */
    @TableId(type = IdType.AUTO)
    private Integer positionId;
    /**
     * 职位名
     */
    @TableField("position_name")
    private String positionName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态 1:有效 0:无效
     */
    private Integer status;


    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

