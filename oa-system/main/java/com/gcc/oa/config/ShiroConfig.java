package com.gcc.oa.config;

import java.util.Properties;

import com.gcc.oa.shiro.CustomCredentialsMatcher;
import com.gcc.oa.shiro.CustomJdbcRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


/**
 * @author 小李探花
 * @className: ShiroConfig
 * @date: 2022/2/6 19:48
 * @description:
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义认证匹配器
     *
     * @return
     */
    @Bean
    public CustomCredentialsMatcher customCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    }

    /**
     * 自定义的安全数据来源
     *
     * @return
     */
    @Bean
    public CustomJdbcRealm customJdbcRealm() {
        CustomJdbcRealm jdbcRealm = new CustomJdbcRealm();
        jdbcRealm.setCredentialsMatcher(customCredentialsMatcher());
        return jdbcRealm;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customJdbcRealm());
        return securityManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 在访问出错的时候跳转的路径
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //没有授权时跳转的控制层
        mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "/unauthorized");
        //没有认证时跳转的控制层
        mappings.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/unauthenticated");
        exceptionResolver.setExceptionMappings(mappings);
        // 访问错误，直接访问登录页面
        exceptionResolver.setDefaultErrorView("http://localhost:8080/");
        return exceptionResolver;
    }

    /**
     * 开启shiro的注解开发,相当于ssm阶段的在配置application.xml文件中配置的 <aop:config proxy-target-class="true"/>
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

}
