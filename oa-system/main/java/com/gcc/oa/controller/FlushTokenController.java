package com.gcc.oa.controller;

import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.UserAuthorizationInfo;
import com.gcc.oa.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @className: FlushTokenController
 * @author: 小李探花
 * @date: 2022/1/16 19:46
 * @description: 刷新token
 */
@RestController
@RequestMapping("token")
@ResponseBody
@Api(tags = "刷新token")
@RequiresAuthentication
public class FlushTokenController {
    @PostMapping("flush")
    @ApiOperation(value = "刷新token的方法")
    public ResponseData flush(@RequestBody UserAuthorizationInfo userAuthorizationInfo) {
        ResponseData token = ResponseData.ok(ExceptionCodeMessage.Message.FLUSH_TOKEN_SUCCESS_MSG);
        token.putDataValue("token", JwtTokenUtil.generateToken(userAuthorizationInfo, new Date(System.currentTimeMillis() + Constants.ONE_HOUR_MILLI_SECOND.getCode()), Constants.TOKEN_CONTENT_NAME.getName()));
        return token;
    }
}
