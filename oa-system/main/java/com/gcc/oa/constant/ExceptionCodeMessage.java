package com.gcc.oa.constant;


/**
 * @className: RespCodeMessage
 * @author: 小李探花
 * @date: 2022/1/18 12:47
 * @description: 响应码与描述信息
 */
public interface ExceptionCodeMessage {
    /**
     * 响应码接口
     */
    interface Code {

        /**
         * 响应成功
         */
        int SUCCESS = 200;

        /**
         * 响应失败
         */
        int OTHER_ERROR = 404;

        /**
         * 用户名或密码错误
         */
        int LOGIN_ERROR = 400;

        /**
         * 验证码不正确
         */
        int VERIFY_ERROR = 401;

        /**
         * 部门的状态修改失败
         */
        int UPDATE_DEPARTMENT_STATUS_FAILED = 402;

        /**
         * 添加部门失败
         */
        int DEPARTMENT_ADD_FAILED = 403;

        /**
         * 修改部门信息失败
         */
        int DEPARTMENT_UPDATE_FAILED = 404;

        /**
         * 录入职位信息失败
         */
        int POSITION_ADD_FAILED = 405;

        /**
         * 修改职位信息失败
         */
        int POSITION_UPDATE_FAILED = 406;

        /**
         * 职位状态修改失败
         */
        int UPDATE_POSITION_STATUS_FAILED = 407;

        /**
         * 员工信息录入失败
         */
        int EMPLOYEE_ADD_FAILED = 408;

        /**
         * 员工信息修改失败
         */
        int EMPLOYEE_UPDATE_FAILED = 409;

        /**
         * 员工信息修改失败
         */
        int UPDATE_EMPLOYEE_STATUS_FAILED = 410;
    }

    /**
     * 响应码对应的描述
     */
    interface Message {

        String LOGIN_SUCCESS_MSG = "登录成功";

        String LOGIN_OUT_SUCCESS_MSG = "退出登录成功";

        String UPDATE_DEPARTMENT_STATUS_SUCCESS_MSG = "部门状态修改成功";

        String UPDATE_DEPARTMENT_STATUS_FAILED_MSG = "部门状态修改失败";

        String QUERY_DEPARTMENT_SUCCESS_MSG = "部门数据刷新成功";

        String FLUSH_TOKEN_SUCCESS_MSG = "token刷新成功";

        String OTHER_ERROR_MSG = "发生了未知错误";

        String LOGIN_ERROR_MSG = "用户名密码错误";

        String VERIFY_ERROR_MSG = "验证码不正确";

        String DEPARTMENT_ADD_FAILED_MSG = "部门添加失败, 请提交完整的信息";

        String DEPARTMENT_ADD_SUCCESS_MSG = "部门添加成功";

        String DEPARTMENT_UPDATE_SUCCESS_MSG = "部门修改成功";

        String DEPARTMENT_UPDATE_FAILED_MSG = "部门信息修改失败";

        String POSITION_UPDATE_SUCCESS_MSG = "职位信息修改成功";

        String POSITION_ADD_SUCCESS_MSG = "职位信息录入成功";

        String POSITION_ADD_FAILED_MSG = "职位信息录入失败";

        String POSITION_UPDATE_FAILED_MSG = "职位信息修改失败";

        String QUERY_POSITION_SUCCESS_MSG = "职位消息刷新成功";

        String UPDATE_POSITION_STATUS_SUCCESS_MSG = "职位信息修改成功";

        String UPDATE_POSITION_STATUS_FAILED_MSG = "职位状态修改失败";

        String EMPLOYEE_UPDATE_SUCCESS_MSG = "员工信息修改成功";

        String EMPLOYEE_ADD_SUCCESS_MSG = "员工信息录入成功";

        String EMPLOYEE_ADD_FAILED_MSG = "员工信息录入失败";

        String EMPLOYEE_UPDATE_FAILED_MSG = "员工信息修改失败";

        String QUERY_EMPLOYEE_SUCCESS_MSG = "员工消息刷新成功";

        String UPDATE_EMPLOYEE_STATUS_SUCCESS_MSG = "员工信息修改成功";

        String UPDATE_EMPLOYEE_STATUS_FAILED_MSG = "员工状态修改失败";

        String INFO_NOT_COMPLETE = "请填写完整的信息再提交!";

        String EMPLOYEE_ALREADY_EXISTS = "该员工已存在!";
    }
}
