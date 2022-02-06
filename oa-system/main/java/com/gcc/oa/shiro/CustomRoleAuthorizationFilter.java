package com.gcc.oa.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @className: CustomAuthorizationFilter
 * @author: 小李探花
 * @date: 2022/2/1 17:15
 * @description: 自定义替代shiro的默认拦截器roles, 目的是为了解决的角色必须全包含问题
 */
public class CustomRoleAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        //判断角色,只要有一个符合都返回true
        for (String role : rolesArray) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
