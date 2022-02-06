package com.gcc.oa.interceptor;

import com.gcc.oa.constant.Constants;
import com.gcc.oa.util.JwtTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: TokenInterceptor
 * @author: 小李探花
 * @date: 2021/12/16 14:04
 * @description: 专门用于检验token的拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Constants.OPTIONS.getName().equals(request.getMethod())) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            try {
                //如果解析失败则会抛出异常
                JwtTokenUtil.parseToken(authorization);
                return true;
            } catch (Exception e) {
                System.err.println("token错误");
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println(request.getRequestURL() + "这个URL在postHandle这里被拦截了");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println(request.getRequestURL() + "这个URL在afterCompletion这里被拦截 了");
    }
}
