package com.gcc.oa.error.position;

import com.gcc.oa.error.BaseException;

/**
 * @className: UpdatePositionStatusFailedException
 * @author: 小李探花
 * @date: 2022/1/24 17:19
 * @description: 职位状态更新失败
 */
public class UpdatePositionStatusFailedException extends BaseException {

    private static final long serialVersionUID = 2198712081796665158L;

    public UpdatePositionStatusFailedException() {
    }

    public UpdatePositionStatusFailedException(int code, String message) {
        super(code, message);
    }
}
