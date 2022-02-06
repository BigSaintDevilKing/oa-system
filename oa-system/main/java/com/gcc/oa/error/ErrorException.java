package com.gcc.oa.error;

/**
 * @className: ErrorException
 * @author: 小李探花
 * @date: 2022/1/18 12:58
 * @description: 错误异常
 */
public class ErrorException extends BaseException {

    private static final long serialVersionUID = 5331968954958512562L;

    /**
     * 响应码
     */
    private int code;
    /**
     * 描述信息
     */
    private String message;

    public ErrorException() {
        super("未知异常");
    }

    public ErrorException(int code, String message) {
        super(message);
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
