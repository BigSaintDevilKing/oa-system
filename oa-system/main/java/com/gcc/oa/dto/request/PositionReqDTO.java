package com.gcc.oa.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gcc.oa.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @className: PositionReqDTO
 * @author: 小李探花
 * @date: 2022/1/24 14:22
 * @description:
 */
@ApiModel(value = "职位请求参数接收的实体类", description = "用于接收与职位请求参数的实体类")
public class PositionReqDTO extends PageDTO {

    private static final long serialVersionUID = 8625529603925898534L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", required = true, example = "2022-1-20 12:12:12")
    private Date createTime;
    /**
     * 状态 1:有效 0:无效
     */
    @ApiModelProperty(value = "部门的是否存在 (1-代表有效, 0-代表无效)", required = true, example = "1")
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

    @Override
    public String toString() {
        return "PositionReqDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                ", positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
