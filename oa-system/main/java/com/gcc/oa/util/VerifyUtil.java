package com.gcc.oa.util;


import javax.servlet.http.HttpServletRequest;

/**
 * @className: VerifyUtil
 * @author: 小李探花
 * @date: 2022/1/19 15:23
 * @description: 处理验证码的工具类
 */
public class VerifyUtil {
    /**
     * 判断验证码是否一致
     *
     * @param req
     * @param verificationCode
     * @return
     */
    public static boolean check(HttpServletRequest req, String verificationCode) {
        String verificationCodeExpected = (String) req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (EmptyUtil.notEmpty(verificationCodeExpected) && EmptyUtil.notEmpty(verificationCode)) {
            return verificationCodeExpected.equalsIgnoreCase(verificationCode);
        }
        return false;
    }
}
