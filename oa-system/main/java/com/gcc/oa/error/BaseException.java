package com.gcc.oa.error;

/**
 * @className: BaseException
 * @author: 小李探花
 * @date: 2022/1/23 11:42
 * @description:
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -3636123849572276047L;

    /**
     * 响应码
     */
    protected int code;
    /**
     * 描述信息
     */
    protected String message;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code, String message) {
        this(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
