package com.gcc.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RoleReqDTO;
import com.gcc.oa.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(Role)表控制层
 *
 * @author 小李探花
 * @since 2022-01-30 19:51:50
 */
@RestController
@RequestMapping("role")
@RequiresPermissions("role-*")
@Api(value = "角色控制层")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @GetMapping("roles")
    @ApiOperation("条件+分页获取角色信息")
    public ResponseData queryPageList(RoleReqDTO reqDTO) {
        return roleService.queryPageList(reqDTO);
    }

    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    @PostMapping("save")
    @ApiOperation("保存角色信息")
    public ResponseData save(@RequestParam String role) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RoleReqDTO roleReqDTO = objectMapper.readValue(role, RoleReqDTO.class);
            //如果roleId不为null则修改
            if (roleReqDTO.getRoleId() != null) {
                responseData = roleService.updateRole(roleReqDTO);
            } else {
                //添加
                responseData = roleService.add(roleReqDTO);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
        }
        return responseData;
    }

    @ApiOperation("获取全部角色信息")
    @GetMapping("all")
    public ResponseData getAll() {
        return roleService.getAll();
    }

}

