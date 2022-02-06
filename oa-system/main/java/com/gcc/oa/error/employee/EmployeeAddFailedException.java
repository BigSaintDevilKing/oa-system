package com.gcc.oa.error.employee;

import com.gcc.oa.error.BaseException;

/**
 * @className: DepartmentAddFailedException
 * @author: 小李探花
 * @date: 2022/1/23 11:41
 * @description: 员工添加失败异常
 */
public class EmployeeAddFailedException extends BaseException {

    private static final long serialVersionUID = 717718602533101047L;

    public EmployeeAddFailedException() {
    }

    public EmployeeAddFailedException(int code, String message) {
        super(code, message);
    }
}
