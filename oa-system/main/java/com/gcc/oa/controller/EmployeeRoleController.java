package com.gcc.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeRoleReqDTO;
import com.gcc.oa.service.EmployeeRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 员工与角色关系表(EmployeeRole)表控制层
 *
 * @author 小李探花
 * @since 2022-01-31 22:47:40
 */
@RestController
@RequestMapping("employeeRole")
@RequiresPermissions("employeeRole-*")
@Api(tags = "员工角色控制层")
public class EmployeeRoleController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeRoleService employeeRoleService;

    /**
     * 新增|修改员工与角色
     *
     * @return
     */
    @PostMapping("save")
    @ApiOperation("保存员工与角色关系")
    public ResponseData save(@RequestParam String employeeRole) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            EmployeeRoleReqDTO reqDTO = objectMapper.readValue(employeeRole, EmployeeRoleReqDTO.class);
            //先删后增
            responseData = employeeRoleService.deleteById(reqDTO.getEmId());
            if (reqDTO.getRoleIds().size() != 0) {
                responseData = employeeRoleService.batchInsert(reqDTO);
            }
        } catch (Exception e) {
            responseData = ResponseData.error(e.getMessage());
        }
        return responseData;
    }

    @ApiOperation("获取指定员工的角色信息")
    @GetMapping("relation/{emId}")
    public ResponseData relation(@PathVariable("emId") Integer emId) {
        return employeeRoleService.queryById(emId);
    }
}

