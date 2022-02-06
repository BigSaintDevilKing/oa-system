package com.gcc.oa.error.department;

import com.gcc.oa.error.BaseException;

/**
 * @className: UpdateDepStatusFailedException
 * @author: 小李探花
 * @date: 2022/1/21 21:18
 * @description: 修改部门的状态失败的异常
 */
public class UpdateDepartmentStatusFailedException extends BaseException {

    private static final long serialVersionUID = 239585552398471899L;

    public UpdateDepartmentStatusFailedException() {
    }

    public UpdateDepartmentStatusFailedException(int code, String message) {
        super(code, message);
    }
}
