package com.gcc.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RolePermissionReqDTO;
import com.gcc.oa.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色与权限关系表(RolePermission)表控制层
 *
 * @author 小李探花
 * @since 2022-01-31 22:48:03
 */
@RestController
@RequestMapping("rolePermission")
@RequiresPermissions("rolePermission-*")
@Api(tags = "角色与权限的关系控制层")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 新增|修改员工与角色
     *
     * @return
     */
    @PostMapping("save")
    @ApiOperation("保存角色与权限关系")
    public ResponseData save(@RequestParam String rolePermission) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RolePermissionReqDTO reqDTO = objectMapper.readValue(rolePermission, RolePermissionReqDTO.class);
            //先删后增
            responseData = rolePermissionService.deleteByRoleId(reqDTO.getRoleId());
            if (reqDTO.getPermissionIds().size() != 0) {
                responseData = rolePermissionService.batchInsert(reqDTO);
            }
        } catch (Exception e) {
            responseData = ResponseData.error(e.getMessage());
        }
        return responseData;
    }

    @GetMapping("relation/{roleId}")
    @ApiOperation("根据角色ID会娶角色与权限信息")
    public ResponseData relation(@PathVariable("roleId") Integer roleId) {
        return rolePermissionService.queryByRoleId(roleId);
    }
}

