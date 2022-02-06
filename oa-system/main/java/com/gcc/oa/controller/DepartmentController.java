package com.gcc.oa.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.error.department.DepartmentAddFailedException;
import com.gcc.oa.error.department.DepartmentUpdateFailedException;
import com.gcc.oa.error.department.UpdateDepartmentStatusFailedException;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.DepartmentReqDTO;
import com.gcc.oa.dto.response.DepartmentRespDTO;
import com.gcc.oa.service.DepartmentService;
import com.gcc.oa.util.EmptyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 部门表(Department)表控制层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
@Api(tags = "部门控制层")
@RestController
@RequestMapping("dep")
@RequiresPermissions("department-*")
public class DepartmentController {

    @Resource
    DepartmentService depService;


    @ApiOperation("涉及分页的获取所有的部门信息")
    @GetMapping("deps")
    public ResponseData queryPageList(DepartmentReqDTO departmentReqDTO) {
        return depService.queryPageList(departmentReqDTO);
    }

    @ApiOperation("获取所有的部门信息")
    @GetMapping("all")
    public ResponseData queryAll() {
        return depService.queryAll();
    }


    @ApiOperation("修改状态")
    @PutMapping("status/{depId}")
    public ResponseData updateStatus(@PathVariable("depId") Integer depId, @RequestBody DepartmentReqDTO reqDTO) {
        ResponseData responseData;
        //如果不为空
        if (EmptyUtil.notEmpty(depId) && EmptyUtil.notEmpty(reqDTO)) {
            try {
                //修改部门状态
                responseData = depService.updateStatus(reqDTO);
                //如果修改成功,则查询修改后的数据,返回给前端
                DepartmentRespDTO respDTO = depService.queryById(reqDTO.getDepId());
                responseData.putDataValue(Constants.DEPARTMENT_KEY_NAME.getName(), respDTO);
            } catch (UpdateDepartmentStatusFailedException e) {
                e.printStackTrace();
                //失败则返回异常信息
                responseData = ResponseData.error(e.getCode(), e.getMessage());
                responseData.putDataValue(Constants.DEPARTMENT_EXCEPTION.getName(), e);
            }
        } else {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.DEPARTMENT_UPDATE_FAILED, ExceptionCodeMessage.Message.DEPARTMENT_UPDATE_FAILED_MSG);
        }
        return responseData;
    }

    @ApiOperation("新增部门或者修改部门")
    @PostMapping("save")
    public ResponseData addDepartment(@RequestParam String department) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //需要把字符串序列化为对象
            DepartmentReqDTO departmentReqDTO = objectMapper.readValue(department, DepartmentReqDTO.class);
            //如果depId存在,则进行修改
            if (EmptyUtil.notEmpty(departmentReqDTO.getDepId())) {
                depService.updateDepartment(departmentReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.DEPARTMENT_UPDATE_SUCCESS_MSG);
            } else {
                depService.addDepartment(departmentReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.DEPARTMENT_ADD_SUCCESS_MSG);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            responseData = ResponseData.error(ExceptionCodeMessage.Code.DEPARTMENT_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.DEPARTMENT_EXCEPTION.getName(), e);
        } catch (DepartmentAddFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.DEPARTMENT_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.DEPARTMENT_EXCEPTION.getName(), e);
        } catch (DepartmentUpdateFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.DEPARTMENT_UPDATE_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.DEPARTMENT_EXCEPTION.getName(), e);
        } catch (Exception e) {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, e.getMessage());
        }
        return responseData;
    }
}

