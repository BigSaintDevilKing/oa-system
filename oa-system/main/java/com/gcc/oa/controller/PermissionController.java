package com.gcc.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PermissionReqDTO;
import com.gcc.oa.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 权限表(Permission)表控制层
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
@RestController
@RequestMapping("per")
@RequiresPermissions("permission-*")
@Api(tags = "权限控制层")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;


    @ApiOperation("获取权限信息")
    @GetMapping("permissions")
    public ResponseData queryPageList(PermissionReqDTO reqDTO) {
        return permissionService.queryPageList(reqDTO);
    }

    /**
     * 新增数据
     *
     * @param permission 实体
     * @return 新增结果
     */
    @PostMapping("save")
    @ApiOperation("保存权限信息")
    public ResponseData save(@RequestParam String permission) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PermissionReqDTO permissionReqDTO = objectMapper.readValue(permission, PermissionReqDTO.class);
            //如果permissionId不为null则修改
            if (permissionReqDTO.getPermissionId() != null) {
                responseData = permissionService.updatePermission(permissionReqDTO);
            } else {
                //添加
                responseData = permissionService.add(permissionReqDTO);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
        }
        return responseData;
    }

    @GetMapping("all")
    @ApiOperation("获取全部权限信息")
    public ResponseData getAll() {
        return permissionService.getAll();
    }

}

