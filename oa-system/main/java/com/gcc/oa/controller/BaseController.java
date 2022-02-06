package com.gcc.oa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: BaseController
 * @author: 小李探花
 * @date: 2022/1/19 15:18
 * @description: 声明了HttpServletRequest对象的基本控制层
 */
public class BaseController {
    @Resource
    protected HttpServletRequest req;
    @Resource
    protected HttpServletResponse resp;
}
