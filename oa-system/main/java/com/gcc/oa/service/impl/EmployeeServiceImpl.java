package com.gcc.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dao.EmployeeDao;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeReqDTO;
import com.gcc.oa.dto.response.EmployeeRespDTO;
import com.gcc.oa.error.employee.EmployeeAddFailedException;
import com.gcc.oa.error.employee.EmployeeUpdateFailedException;
import com.gcc.oa.error.employee.UpdateEmployeeStatusFailedException;
import com.gcc.oa.error.position.PositionUpdateFailedException;
import com.gcc.oa.service.EmployeeService;
import com.gcc.oa.util.EmptyUtil;
import com.gcc.oa.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工表(Employee)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public EmployeeRespDTO queryByUsernameAndPwd(String loginName, String password) {
        return employeeDao.queryByUsernameAndPwd(loginName, password);
    }

    /**
     * 分页+条件查询
     *
     * @param employeeReqDTO
     * @return
     */
    @Override
    public ResponseData queryPageList(EmployeeReqDTO employeeReqDTO) {
        ResponseData responseData;
        try {
            QueryWrapper<EmployeeReqDTO> condition = new QueryWrapper<>();
            if (EmptyUtil.notEmpty(employeeReqDTO.getName())) {
                condition.like("name", employeeReqDTO.getName());
            }
            if (EmptyUtil.notEmpty(employeeReqDTO.getDepId())) {
                condition.eq("dep_id", employeeReqDTO.getDepId());
            }
            if (EmptyUtil.notEmpty(employeeReqDTO.getPositionId())) {
                condition.eq("position_id", employeeReqDTO.getPositionId());
            }
            if (EmptyUtil.notEmpty(employeeReqDTO.getStatus())) {
                condition.eq("status", employeeReqDTO.getStatus());
            }
            Page<EmployeeRespDTO> page = new Page<>(employeeReqDTO.getCurrent(), employeeReqDTO.getSize());
            IPage<EmployeeRespDTO> employeeRespDTO = employeeDao.queryPageList(page, condition);
            //完善statusDesc字段
            for (EmployeeRespDTO respDTO : employeeRespDTO.getRecords()) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc(Constants.EMPLOYEE_EFFECTIVE_STATUS_DESC.getName());
                } else {
                    respDTO.setStatusDesc(Constants.EMPLOYEE_UN_EFFECTIVE_STATUS_DESC.getName());
                }
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_EMPLOYEE_SUCCESS_MSG);
            responseData.putDataValue(Constants.EMPLOYEE_LIST_KEY_NAME.getName(), employeeRespDTO);
        } catch (Exception e) {
            responseData = ResponseData.otherError();
            //由于数据的查询不涉及事务, 所以不需要抛出异常
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public ResponseData updateStatus(EmployeeReqDTO reqDTO) {
        ResponseData responseData;
        try {
            //原本数据是有效的状态
            if (reqDTO.getStatus() == 1) {
                //把数据变为无效的状态
                employeeDao.updateStatusToOFF(reqDTO.getEmId());
            } else {
                //把数据变为有效的状态
                employeeDao.updateStatusToON(reqDTO.getEmId());
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.UPDATE_EMPLOYEE_STATUS_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateEmployeeStatusFailedException(ExceptionCodeMessage.Code.UPDATE_EMPLOYEE_STATUS_FAILED,
                    ExceptionCodeMessage.Message.UPDATE_EMPLOYEE_STATUS_FAILED_MSG);
        }
        return responseData;
    }

    @Override
    public EmployeeRespDTO queryById(Integer emId) {
        EmployeeRespDTO respDTO = employeeDao.queryById(emId);
        if (EmptyUtil.notEmpty(respDTO)) {
            if (respDTO.getStatus() == 1) {
                respDTO.setStatusDesc(Constants.EMPLOYEE_EFFECTIVE_STATUS_DESC.getName());
            } else {
                respDTO.setStatusDesc(Constants.EMPLOYEE_UN_EFFECTIVE_STATUS_DESC.getName());
            }
        }
        return respDTO;
    }

    /**
     * 添加员工
     *
     * @param employeeReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void addEmployee(EmployeeReqDTO employeeReqDTO) {
        try {
            if (EmptyUtil.notEmpty(employeeReqDTO) && EmptyUtil.notEmpty(employeeReqDTO.getName())
                    && EmptyUtil.notEmpty(employeeReqDTO.getPositionId())
                    && EmptyUtil.notEmpty(employeeReqDTO.getDepId())
                    && EmptyUtil.notEmpty(employeeReqDTO.getStatus())
                    && EmptyUtil.notEmpty(employeeReqDTO.getLoginName())
                    && EmptyUtil.notEmpty(employeeReqDTO.getName())
                    && EmptyUtil.notEmpty(employeeReqDTO.getPassword())
            ) {
                //判断账号和密码是否已存在
                if (employeeDao.queryByLoginName(employeeReqDTO.getLoginName()).size() == 0) {
                    //加密密码
                    employeeReqDTO.setPassword(MD5Util.encryption(employeeReqDTO.getPassword()));
                    employeeDao.addEmployee(employeeReqDTO);
                } else {
                    throw new EmployeeAddFailedException(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                            ExceptionCodeMessage.Message.EMPLOYEE_ALREADY_EXISTS);
                }
            } else {
                throw new EmployeeAddFailedException(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                        ExceptionCodeMessage.Message.INFO_NOT_COMPLETE);
            }
        } catch (EmployeeAddFailedException e) {
            System.err.println(e.getMessage());
            throw new EmployeeAddFailedException(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                    e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new EmployeeAddFailedException(ExceptionCodeMessage.Code.EMPLOYEE_ADD_FAILED,
                    ExceptionCodeMessage.Message.EMPLOYEE_ADD_FAILED_MSG);
        }
    }

    /**
     * 更新员工
     *
     * @param employeeReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void updateEmployee(EmployeeReqDTO employeeReqDTO) {
        try {
            EmployeeRespDTO employeeRespDTO = employeeDao.queryById(employeeReqDTO.getEmId());
            if (!employeeReqDTO.getLoginName().equals(employeeRespDTO.getLoginName())) {
                //判断新登陆账号是否已经存在
                List<EmployeeRespDTO> employeeRespDTOS = employeeDao.queryByLoginName(employeeReqDTO.getLoginName());
                if (employeeRespDTOS.size() == 0) {
                    employeeDao.updateEmployee(employeeReqDTO);
                } else {
                    throw new EmployeeUpdateFailedException(ExceptionCodeMessage.Code.EMPLOYEE_UPDATE_FAILED,
                            ExceptionCodeMessage.Message.EMPLOYEE_ALREADY_EXISTS);
                }
            }
            employeeDao.updateEmployee(employeeReqDTO);
        } catch (EmployeeUpdateFailedException e) {
            System.err.println(e.getMessage());
            throw new PositionUpdateFailedException(ExceptionCodeMessage.Code.EMPLOYEE_UPDATE_FAILED,
                    e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new PositionUpdateFailedException(ExceptionCodeMessage.Code.EMPLOYEE_UPDATE_FAILED,
                    ExceptionCodeMessage.Message.EMPLOYEE_UPDATE_FAILED_MSG);
        }
    }

    @Override
    public String queryPasswordByUsername(String loginName) {
        return employeeDao.queryPasswordByUsername(loginName);
    }

    @Override
    public EmployeeRespDTO queryUserInfoByLoginName(String loginName) {
        return employeeDao.queryUserInfoByLoginName(loginName);
    }
}

