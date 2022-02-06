package com.gcc.oa.error.department;

import com.gcc.oa.error.BaseException;

/**
 * @className: DepartmentAddFailedException
 * @author: 小李探花
 * @date: 2022/1/23 11:41
 * @description: 部门添加失败异常
 */
public class DepartmentAddFailedException extends BaseException {

    private static final long serialVersionUID = 717718602533101047L;

    public DepartmentAddFailedException() {
    }

    public DepartmentAddFailedException(int code, String message) {
        super(code, message);
    }
}
