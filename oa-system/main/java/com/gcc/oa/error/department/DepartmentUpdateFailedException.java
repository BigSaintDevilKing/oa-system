package com.gcc.oa.error.department;

import com.gcc.oa.error.BaseException;

/**
 * @className: DepartmentUpdateFailedException
 * @author: 小李探花
 * @date: 2022/1/23 16:55
 * @description: 部门修改失败异常
 */
public class DepartmentUpdateFailedException extends BaseException {

    private static final long serialVersionUID = -7720771474532879551L;

    public DepartmentUpdateFailedException() {
    }

    public DepartmentUpdateFailedException(int code, String message) {
        super(code, message);
    }
}
