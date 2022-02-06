package com.gcc.oa.config;

import com.gcc.oa.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: TokenInterceptor
 * @author: 小李探花
 * @date: 2022/1/26 19:26
 * @description:
 */
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new TokenInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //添加不拦截路径
        registration.excludePathPatterns(
                "/login",
                "/loginOut",
                "/kaptcha",
                "/swagger-resources/**",
                "/webjars/**",
                "/swagger-ui.html/**",
                "/v2/**",
                "/**.ico",
                "/**.html",
                "/**.css",
                "/**.js"
        );
    }
}
