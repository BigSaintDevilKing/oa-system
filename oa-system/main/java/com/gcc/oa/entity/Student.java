package com.gcc.oa.entity;

import java.io.Serializable;

/**
 * @className: Student
 * @author: 小李探花
 * @date: 2022/1/26 21:14
 * @description:
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 2566371145502599490L;
    private Integer stuId;
    private String stuName;
    private Integer courseId;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
