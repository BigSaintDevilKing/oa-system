package com.gcc.oa.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeReqDTO;
import com.gcc.oa.dto.response.EmployeeRespDTO;
import com.gcc.oa.error.ErrorException;
import com.gcc.oa.error.employee.EmployeeAddFailedException;
import com.gcc.oa.error.employee.EmployeeUpdateFailedException;
import com.gcc.oa.error.position.UpdatePositionStatusFailedException;
import com.gcc.oa.service.EmployeeService;
import com.gcc.oa.util.EmptyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 员工表(Employee)表控制层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:28
 */
@RestController
@RequestMapping("employee")
@Api(tags = "员工控制层")
@RequiresPermissions(value = {"employee-*"})
public class EmployeeController {

    /**
     * 员工服务层对象
     */
    @Resource
    private EmployeeService employeeService;

    @GetMapping("employees")
    @ApiOperation("条件+分页查询员工信息")
    public ResponseData queryPageList(EmployeeReqDTO employeeReqDTO) {
        return employeeService.queryPageList(employeeReqDTO);
    }

    @GetMapping("flush")
    @ApiOperation("根据ID获取员工信息")
    public ResponseData flush(@RequestParam Integer emId) {
        ResponseData responseData;
        if (EmptyUtil.notEmpty(emId)) {
            try {
                EmployeeRespDTO employeeRespDTO = employeeService.queryById(emId);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_EMPLOYEE_SUCCESS_MSG);
                responseData.putDataValue(Constants.EMPLOYEE_KEY_NAME.getName(), employeeRespDTO);
            } catch (Exception e) {
                responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, ExceptionCodeMessage.Message.OTHER_ERROR_MSG);
                responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), e);
            }
        } else {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, ExceptionCodeMessage.Message.OTHER_ERROR_MSG);
            responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), new ErrorException());
        }
        return responseData;
    }


    @PutMapping("/status/{emId}")
    @ApiOperation("根据ID修改员工信息")
    public ResponseData updateStatus(@PathVariable("emId") Integer emId, @RequestBody EmployeeReqDTO reqDTO) {
        ResponseData responseData;
        //如果不为空
        if (EmptyUtil.notEmpty(emId) && EmptyUtil.notEmpty(reqDTO)) {
            try {
                //修改职位状态
                responseData = employeeService.updateStatus(reqDTO);
                //如果修改成功,则查询修改后的数据,返回给前端
                EmployeeRespDTO respDTO = employeeService.queryById(reqDTO.getEmId());
                responseData.putDataValue(Constants.EMPLOYEE_KEY_NAME.getName(), respDTO);
            } catch (UpdatePositionStatusFailedException e) {
                e.printStackTrace();
                //失败则返回异常信息
                responseData = ResponseData.error(e.getCode(), e.getMessage());
                responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), e);
            }
        } else {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.EMPLOYEE_UPDATE_FAILED, ExceptionCodeMessage.Message.EMPLOYEE_UPDATE_FAILED_MSG);
        }
        return responseData;
    }

    @ApiOperation("新增员工或者修改员工")
    @PostMapping("save")
    public ResponseData saveEmployee(@RequestParam String employee) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //需要把字符串序列化为对象
            EmployeeReqDTO employeeReqDTO = objectMapper.readValue(employee, EmployeeReqDTO.class);
            //如果emId存在,则进行修改
            if (EmptyUtil.notEmpty(employeeReqDTO.getEmId())) {
                employeeService.updateEmployee(employeeReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.EMPLOYEE_UPDATE_SUCCESS_MSG);
            } else {
                employeeService.addEmployee(employeeReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.EMPLOYEE_ADD_SUCCESS_MSG);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            responseData = ResponseData.error(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), e);
        } catch (EmployeeAddFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), e);
        } catch (EmployeeUpdateFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.EMPLOYEE_UPDATE_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.EMPLOYEE_EXCEPTION.getName(), e);
        } catch (Exception e) {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, e.getMessage());
        }
        return responseData;
    }
}

