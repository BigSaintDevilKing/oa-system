package com.gcc.oa.error.employee;

import com.gcc.oa.error.BaseException;

/**
 * @className: DepartmentUpdateFailedException
 * @author: 小李探花
 * @date: 2022/1/23 16:55
 * @description: 员工修改失败异常
 */
public class EmployeeUpdateFailedException extends BaseException {

    private static final long serialVersionUID = -7720771474532879551L;

    public EmployeeUpdateFailedException() {
    }

    public EmployeeUpdateFailedException(int code, String message) {
        super(code, message);
    }
}
