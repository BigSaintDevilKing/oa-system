package com.gcc.oa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: PositionRespDTO
 * @author: 小李探花
 * @date: 2022/1/22 0:28
 * @description:
 */
@ApiModel(value = "职位响应实体类", description = "用于响应关于职位请求的实体类")
public class PositionRespDTO implements Serializable {
    private static final long serialVersionUID = -4435237548088788582L;
    /**
     * 职位表id
     */
    @ApiModelProperty(value = "职位的ID", required = true, example = "1")
    private Integer positionId;
    /**
     * 职位名
     */
    @ApiModelProperty(value = "职位的名称", required = true, example = "普通员工")
    private String positionName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true, example = "2022-1-20 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 状态 1:有效 0:无效
     */
    @ApiModelProperty(value = "部门的是否存在 (1-代表有效, 0-代表无效)", required = true, example = "1")
    private Integer status;

    @ApiModelProperty(value = "对职位状态的说明 (1-代表有效, 0-代表无效)", required = true, example = "有效")
    private String statusDesc;


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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "PositionRespDTO{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
