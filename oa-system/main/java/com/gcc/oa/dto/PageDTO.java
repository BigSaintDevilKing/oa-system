package com.gcc.oa.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @className: PageDTO
 * @author: 小李探花
 * @date: 2022/1/24 0:26
 * @description: 分页参数的DTO
 */
@ApiModel(value = "包含分页相关信息的DTO")
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 1393570231125440862L;

    @ApiModelProperty(value = "总记录数")
    protected Long total;

    @ApiModelProperty(value = "总页数")
    protected Long pages;

    @ApiModelProperty(value = "当前页数")
    protected Long current;

    @ApiModelProperty(value = "每页显示多少条记录")
    protected Long size;

    public PageDTO() {
        current = 1L;
        size = 7L;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                '}';
    }
}
