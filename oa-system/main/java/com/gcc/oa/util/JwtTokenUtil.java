package com.gcc.oa.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: JwtTokenUtil
 * @author: 小李探花
 * @date: 2022/1/16 14:48
 * @description: JWT操作token的工具类
 */
public class JwtTokenUtil {

    /**
     * 签名使用的密钥
     */
    private static final String KEY = "1234567890@qq.com&13500000000$I#LOVE*YOU";

    /**
     * 生成token
     *
     * @param obj  需要传入的主要内容
     * @param date 过期时间
     * @param key
     * @return
     */
    public static String generateToken(Object obj, Date date, String key) {
        //生成令牌
        //创建JwtBuilder
        JwtBuilder builder = Jwts.builder();
        //1.头(不设置,默认) 2 载荷(数据) 3. 签名
        //声明唯一的标识
        builder.setId("jwt-iso8859-1")
                //生成令牌的一方
                .setIssuer("ssm项目后端管理者")
                //就是描述 jwt的信息
                .setSubject("登录用户")
                //签发日期
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(date)
                //设置签名
                .signWith(SignatureAlgorithm.HS256, JwtTokenUtil.KEY);
        //3.可以自定义载荷
        Map<String, Object> map = new HashMap<>(10);
        map.put(key, obj);
        builder.addClaims(map);
        //生成令牌
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token 令牌
     * @return 自定义的载荷
     */
    public static Claims parseToken(String token) {
        //解析令牌
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }
}
