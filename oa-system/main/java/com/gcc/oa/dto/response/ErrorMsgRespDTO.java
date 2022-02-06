package com.gcc.oa.dto.response;

import java.io.Serializable;

/**
 * @className: ExceptionMsgDTO
 * @author: 小李探花
 * @date: 2022/2/4 23:27
 * @description: 错误信息响应的实体类
 */
public class ErrorMsgRespDTO implements Serializable {
    private static final long serialVersionUID = 6035347659540327171L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    public ErrorMsgRespDTO() {
    }

    public ErrorMsgRespDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
