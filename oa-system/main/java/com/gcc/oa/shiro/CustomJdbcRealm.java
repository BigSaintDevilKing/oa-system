package com.gcc.oa.shiro;

import com.gcc.oa.dto.UserAuthorizationInfo;
import com.gcc.oa.dto.response.EmployeeRespDTO;
import com.gcc.oa.dto.response.EmployeeRoleRespDTO;
import com.gcc.oa.dto.response.RolePermissionRespDTO;
import com.gcc.oa.service.EmployeeRoleService;
import com.gcc.oa.service.EmployeeService;
import com.gcc.oa.service.RolePermissionService;
import com.gcc.oa.util.EmptyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: JdbcRealms
 * @author: 小李探花
 * @date: 2022/1/29 12:17
 * @description: 自定义的安全数据来源域
 */
public class CustomJdbcRealm extends AuthorizingRealm {


    @Resource
    EmployeeService employeeService;

    @Resource
    EmployeeRoleService employeeRoleService;

    @Resource
    RolePermissionService rolePermissionService;


    private void queryAuthorization(PrincipalCollection principals) {
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //获取用户当前用户
        EmployeeRespDTO employeeRespDTO = (EmployeeRespDTO) principals.getPrimaryPrincipal();
        //获取角色
        List<EmployeeRoleRespDTO> roles = employeeRoleService.queryModelByEmId(employeeRespDTO.getEmId());
        //添加权限
        for (EmployeeRoleRespDTO role : roles) {
            roleSet.add(role.getRoleRespDTO().getRoleName());
            List<RolePermissionRespDTO> permissions = rolePermissionService.queryModelByRoleId(role.getRoleId());
            for (RolePermissionRespDTO permission : permissions) {
                permissionSet.add(permission.getPermissionRespDTO().getPermissionName());
            }
        }
        UserAuthorizationInfo userAuthorizationInfo = new UserAuthorizationInfo(roleSet, permissionSet);
        SecurityUtils.getSubject().getSession().setAttribute("userAuthorizationInfo", userAuthorizationInfo);
    }


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        queryAuthorization(principals);
        UserAuthorizationInfo userAuthorizationInfo = (UserAuthorizationInfo) SecurityUtils.getSubject().getSession().getAttribute("userAuthorizationInfo");
        authorizationInfo.addRoles(userAuthorizationInfo.getRoles());
        authorizationInfo.addStringPermissions(userAuthorizationInfo.getPermissions());
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        SimpleAuthenticationInfo info;
        try {
            String password = employeeService.queryPasswordByUsername(upToken.getUsername());
            if (EmptyUtil.notEmpty(password)) {
                EmployeeRespDTO employeeRespDTO = employeeService.queryUserInfoByLoginName(username);
                info = new SimpleAuthenticationInfo(employeeRespDTO, password, getName());
            } else {
                throw new UnknownAccountException("No account found for user [" + username + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
            final String message = "There was a SQL error while authenticating user [" + username + "]";
            // Rethrow any SQL errors as an authentication exception
            throw new AuthenticationException(message, e);
        }
        queryAuthorization(info.getPrincipals());
        return info;
    }
}
