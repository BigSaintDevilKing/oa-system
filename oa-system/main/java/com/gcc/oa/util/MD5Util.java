package com.gcc.oa.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * MD5加密工具类
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:28
 */
public class MD5Util {
    /**
     * 获得摘要
     *
     * @param text 需要加密的字符串
     * @return 字符串
     */
    public static String MD5(String text) {
        char[] hexDigits = {'5', '2', '0', '#', '*', '&', '/', '=', '%', '$', '@', 'L', 'O', 'V', 'E', 'U'};

        try {
            byte[] btInput = text.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return "123456";
        }
    }


    /**
     * 生成密文, 用于加密字符串
     *
     * @param password 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryption(String password) {
        String salt = genSalt();
        password = MD5(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }


    /**
     * 生成固定盐值
     *
     * @return String
     */
    private static String genSalt() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        return sb.toString();
    }


    /**
     * 解密, 用于校验使用encryption加密的字符串
     *
     * @param password 需要校验的字符串
     * @param md5      已加密的字符串
     * @return true | false
     */
    public static boolean decrypt(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        String key = MD5(password + salt);
        return key.equals(new String(cs1));
    }
}
