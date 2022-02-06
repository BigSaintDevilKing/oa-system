package com.gcc.oa.error.position;

import com.gcc.oa.error.BaseException;

/**
 * @className: PositionAddFailedException
 * @author: 小李探花
 * @date: 2022/1/24 17:22
 * @description: 职位添加失败
 */
public class PositionAddFailedException extends BaseException {

    private static final long serialVersionUID = 529294536825790056L;

    public PositionAddFailedException() {
    }

    public PositionAddFailedException(int code, String message) {
        super(code, message);
    }
}
