package com.gcc.oa.dto;

import com.gcc.oa.constant.ExceptionCodeMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: ResponseData
 * @author: 小李探花
 * @date: 2022/1/16 14:43
 * @description: 公共返回的数据对象
 */
@ApiModel(value = "数据响应实体类", description = "统一采用的数据响应实体类")
public class ResponseData implements Serializable {

    private static final long serialVersionUID = -8217249324119948883L;

    @ApiModelProperty(value = "返回的状态信息, 与状态码一一对应", required = true, example = "响应成功")
    private final String message;

    @ApiModelProperty(value = "返回的状态码, 与状态信息一一对应", required = true, example = "200")
    private final int code;

    @ApiModelProperty(value = "数据封装集合, 数据主要从这里取用于存储数据, 数据主要从这里取", required = true, example = "token=XXX")
    private final Map<String, Object> dataMap = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void putDataValue(String key, Object value) {
        dataMap.put(key, value);
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 正常返回
     *
     * @return
     */
    public static ResponseData ok(String message) {
        return new ResponseData(ExceptionCodeMessage.Code.SUCCESS, message);
    }

    /**
     * 发生未知错误
     *
     * @return
     */
    public static ResponseData otherError() {
        return new ResponseData(ExceptionCodeMessage.Code.OTHER_ERROR, ExceptionCodeMessage.Message.OTHER_ERROR_MSG);
    }

    /**
     * 发生未知的异常,可自定义信息
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseData otherError(int code, String message) {
        return new ResponseData(code, message);
    }


    /**
     * 自定义具体错误
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseData error(int code, String message) {
        return new ResponseData(code, message);
    }
}
