package com.gcc.oa.constant;


/**
 * @className: Constants
 * @author: 小李探花
 * @date: 2022/1/19 21:05
 * @description: 常量类, 专门由于存储在ResponseData的key和value
 */
public enum Constants {

    /**
     * token令牌的主题部分的键名为employee
     */
    TOKEN_CONTENT_NAME(1, "authorization"),
    /**
     * 部门列表数据在ResponseData中的key值为departments
     */
    DEPARTMENT_LIST_KEY_NAME(2, "departments"),
    /**
     * 员工信息在responseData的键名为employee
     */
    EMPLOYEE_KEY_NAME(3, "employee"),
    /**
     * 员工在responseData的键名为employee
     */
    TOKEN_KEY_NAME(4, "token"),
    /**
     * 员工在responseData的键名为employee
     */
    LOGIN_ERROR_EXCEPTION(5, "exception"),
    /**
     * 部门异常
     */
    DEPARTMENT_EXCEPTION(6, "exception"),
    /**
     * 有效状态的说明
     */
    EFFECTIVE_STATUS_DESC(7, "有效"),
    /**
     * 无效状态的说明
     */
    UN_EFFECTIVE_STATUS_DESC(8, "无效"),
    /**
     * 部门的对象在ResponseData中的键名
     */
    DEPARTMENT_KEY_NAME(10, "department"),
    /**
     * 职位的对象在ResponseData中的键名
     */
    POSITION_KEY_NAME(11, "position"),
    /**
     * 职位异常
     */
    POSITION_EXCEPTION(12, "exception"),
    /**
     * 部门列表数据在ResponseData中的key值为positions
     */
    POSITION_LIST_KEY_NAME(13, "positions"),
    /**
     * 员工异常
     */
    EMPLOYEE_EXCEPTION(14, "exception"),
    /**
     * 员工列表数据在ResponseData中的key值为employees
     */
    EMPLOYEE_LIST_KEY_NAME(15, "employees"),
    /**
     * 员工在职状态说明
     */
    EMPLOYEE_EFFECTIVE_STATUS_DESC(16, "在职"),
    /**
     * 员工离职状态说明
     */
    EMPLOYEE_UN_EFFECTIVE_STATUS_DESC(16, "离职"),
    /**
     * 一小时的毫秒值
     */
    ONE_HOUR_MILLI_SECOND(60 * 60 * 1000, "一小时的毫秒值"),
    /**
     * 预检请求的请求方法
     */
    OPTIONS(891, "OPTIONS");

    private int code;
    private String name;

    Constants(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
