package com.gcc.oa.error.employee;

import com.gcc.oa.error.BaseException;

/**
 * @className: UpdateDepStatusFailedException
 * @author: 小李探花
 * @date: 2022/1/21 21:18
 * @description: 员工部门的状态失败的异常
 */
public class UpdateEmployeeStatusFailedException extends BaseException {

    private static final long serialVersionUID = 239585552398471899L;

    public UpdateEmployeeStatusFailedException() {
    }

    public UpdateEmployeeStatusFailedException(int code, String message) {
        super(code, message);
    }
}
