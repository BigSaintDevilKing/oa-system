package com.gcc.oa.util;

/**
 * @className: EmptyUtil
 * @author: 小李探花
 * @date: 2022/1/18 14:20
 * @description: 校验是否为空的工具类
 */
public class EmptyUtil {
    /**
     * 是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 是否为空字符串
     *
     * @param obj
     * @return
     */
    public static boolean isEmptyString(Object obj) {
        return "" == obj;
    }

    /**
     * 是否为空或者空字符串
     *
     * @param obj
     * @return
     */
    public static boolean notEmpty(Object obj) {
        return !isNull(obj) && !isEmptyString(obj);
    }
}
