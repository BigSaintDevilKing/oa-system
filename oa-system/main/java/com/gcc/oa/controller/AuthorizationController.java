package com.gcc.oa.controller;

import com.gcc.oa.constant.Constants;
import com.gcc.oa.dto.response.ErrorMsgRespDTO;
import com.gcc.oa.dto.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: AuthorizationController
 * @author: 小李探花
 * @date: 2022/2/3 17:21
 * @description:
 */
@RestController
@RequiresAuthentication
@Api(tags = "验证控制层")
public class AuthorizationController {

    @ApiOperation("获取相关的授权信息")
    @PostMapping("authorization")
    public ResponseData getAuthorization() {
        ResponseData responseData = ResponseData.ok("权限查询成功");
        Subject subject = SecurityUtils.getSubject();
        responseData.putDataValue(Constants.TOKEN_CONTENT_NAME.getName(), subject.getSession().getAttribute("userAuthorizationInfo"));
        return responseData;
    }

    @ApiOperation("没有授权成功返回信息")
    @GetMapping("unauthorized")
    public ResponseData unauthorizedException() {
        return ResponseData.error(403, new ErrorMsgRespDTO(403, "你没有访问权限").toString());
    }

    @ApiOperation("没有认证成功返回的信息")
    @GetMapping("unauthenticated")
    public ResponseData unauthenticatedException() {
        return ResponseData.error(403, new ErrorMsgRespDTO(401, "你没有认证,不能访问").toString());
    }
}
