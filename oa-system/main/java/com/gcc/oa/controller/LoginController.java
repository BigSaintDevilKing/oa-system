package com.gcc.oa.controller;

import com.gcc.oa.error.ErrorException;

import static com.gcc.oa.constant.ExceptionCodeMessage.*;

import com.gcc.oa.constant.Constants;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeReqDTO;
import com.gcc.oa.dto.response.EmployeeRespDTO;
import com.gcc.oa.util.EmptyUtil;
import com.gcc.oa.util.JwtTokenUtil;
import com.gcc.oa.util.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @className: LoginController
 * @author: 小李探花
 * @date: 2022/1/18 13:34
 * @description: 登录控制层
 */
@Api(tags = "登录层")
@ResponseBody
@RestController
public class LoginController extends BaseController {

    @PostMapping("/login")
    @ApiOperation(value = "检验登录")
    public ResponseData login(@RequestBody EmployeeReqDTO employee) {
        ResponseData responseData;
        if (EmptyUtil.notEmpty(employee)) {
            //第一步先判断验证码
            String verificationCode = employee.getVerificationCode();
            //如果验证码校验失败
            if (!VerifyUtil.check(req, verificationCode)) {
                responseData = ResponseData.error(Code.VERIFY_ERROR, Message.VERIFY_ERROR_MSG);
                return responseData;
            }
            String loginName = employee.getLoginName();
            String password = employee.getPassword();
            //如果获取到的参数不为空
            if (EmptyUtil.notEmpty(loginName) && EmptyUtil.notEmpty(password)) {
                //获取提交的主体和凭据
                UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
                //获取当前用户
                Subject currentUser = SecurityUtils.getSubject();
                //永不过时
                currentUser.getSession().setTimeout(-1);
                try {
                    //登录
                    currentUser.login(token);
                    if (currentUser.isAuthenticated()) {
                        EmployeeRespDTO userInfo = (EmployeeRespDTO) currentUser.getPrincipal();
                        responseData = ResponseData.ok(Message.LOGIN_SUCCESS_MSG);
                        responseData.putDataValue(Constants.EMPLOYEE_KEY_NAME.getName(), userInfo);
                        responseData.putDataValue(Constants.TOKEN_KEY_NAME.getName(), JwtTokenUtil.generateToken(
                                currentUser.getSession().getAttribute("userAuthorizationInfo"), new Date(System.currentTimeMillis() + Constants.ONE_HOUR_MILLI_SECOND.getCode()),
                                Constants.TOKEN_CONTENT_NAME.getName()));
                        return responseData;
                    }
                } catch (AuthenticationException e) {
                    System.err.println("用户名或密码错误: " + e.getMessage());
                    //失败,并把异常信息返回
                    //查询失败,返回错误的提示信息
                    responseData = ResponseData.error(Code.LOGIN_ERROR, Message.LOGIN_ERROR_MSG);
                    ErrorException errorException = new ErrorException(responseData.getCode(), responseData.getMessage());
                    //把异常信息返回
                    responseData.putDataValue(Constants.LOGIN_ERROR_EXCEPTION.getName(), errorException);
                    return responseData;
                }
            }
        }
        //查询失败,返回错误的提示信息
        responseData = ResponseData.error(Code.LOGIN_ERROR, Message.LOGIN_ERROR_MSG);
        ErrorException errorException = new ErrorException(responseData.getCode(), responseData.getMessage());
        //把异常信息返回
        responseData.putDataValue(Constants.LOGIN_ERROR_EXCEPTION.getName(), errorException);
        return responseData;
    }

    @ApiOperation("退出登录")
    @PostMapping("/loginOut")
    @RequiresAuthentication
    public ResponseData loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if (subject.isAuthenticated()) {
            System.err.println("退出登录异常");
        }
        return ResponseData.ok(Message.LOGIN_OUT_SUCCESS_MSG);
    }
}
