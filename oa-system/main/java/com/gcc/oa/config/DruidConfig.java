package com.gcc.oa.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;

/**
 * @className: Druid
 * @author: 小李探花
 * @date: 2022/1/13 13:34
 * @description:
 */
@Configuration
public class DruidConfig {
    /**
     * 注册Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean<Servlet> servletRegistrationBean() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "lzh");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }

    /**
     * 注册自定义Filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "/druid/*,*.js,*.gif,*.jpg,*.png ,*.css,*.ico");
        return filterRegistrationBean;
    }
}
