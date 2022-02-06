package com.gcc.oa.shiro;

import com.gcc.oa.util.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @className: MD5CredentialsMatcher
 * @author: 小李探花
 * @date: 2022/1/29 12:36
 * @description: 自定义的密码匹配器
 */
public class CustomCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //用户明文的密码
        String password = new String(upToken.getPassword());
        String md5Password = (String) info.getCredentials();
        return MD5Util.decrypt(password, md5Password);
    }
}
