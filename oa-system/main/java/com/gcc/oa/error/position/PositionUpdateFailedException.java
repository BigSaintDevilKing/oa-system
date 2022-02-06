package com.gcc.oa.error.position;

import com.gcc.oa.error.BaseException;

/**
 * @className: PositionUpdateFailedException
 * @author: 小李探花
 * @date: 2022/1/24 17:24
 * @description: 职位信息修改失败
 */
public class PositionUpdateFailedException extends BaseException {

    private static final long serialVersionUID = -7939138776509901029L;

    public PositionUpdateFailedException() {
    }

    public PositionUpdateFailedException(int code, String message) {
        super(code, message);
    }
}
