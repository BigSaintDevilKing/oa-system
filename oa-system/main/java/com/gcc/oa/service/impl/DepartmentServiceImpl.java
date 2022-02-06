package com.gcc.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.error.department.DepartmentAddFailedException;
import com.gcc.oa.error.department.DepartmentUpdateFailedException;
import com.gcc.oa.error.department.UpdateDepartmentStatusFailedException;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dao.DepartmentDao;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.DepartmentReqDTO;
import com.gcc.oa.dto.response.DepartmentRespDTO;
import com.gcc.oa.service.DepartmentService;
import com.gcc.oa.util.EmptyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(Department)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    /**
     * 分页+条件查询
     *
     * @param departmentReqDTO
     * @return
     */
    @Override
    public ResponseData queryPageList(DepartmentReqDTO departmentReqDTO) {
        ResponseData responseData;
        try {
            QueryWrapper<DepartmentReqDTO> condition = new QueryWrapper<>();
            if (EmptyUtil.notEmpty(departmentReqDTO.getName())) {
                condition.like("name", departmentReqDTO.getName());
            } else if (EmptyUtil.notEmpty(departmentReqDTO.getAddress())) {
                condition.like("address", departmentReqDTO.getAddress());
            }
            if (EmptyUtil.notEmpty(departmentReqDTO.getStatus())) {
                condition.eq("status", departmentReqDTO.getStatus());
            }
            Page<DepartmentRespDTO> page = new Page<>(departmentReqDTO.getCurrent(), departmentReqDTO.getSize());
            IPage<DepartmentRespDTO> departmentRespDTOS = departmentDao.queryPageList(page, condition);
            //完善statusDesc字段
            for (DepartmentRespDTO respDTO : departmentRespDTOS.getRecords()) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc(Constants.EFFECTIVE_STATUS_DESC.getName());
                } else {
                    respDTO.setStatusDesc(Constants.UN_EFFECTIVE_STATUS_DESC.getName());
                }
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_DEPARTMENT_SUCCESS_MSG);
            responseData.putDataValue(Constants.DEPARTMENT_LIST_KEY_NAME.getName(), departmentRespDTOS);
        } catch (Exception e) {
            responseData = ResponseData.otherError();
            //由于数据的查询不涉及事务, 所以不需要抛出异常
            e.printStackTrace();
        }
        return responseData;
    }

    /**
     * 修改部门的状态
     *
     * @param reqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public ResponseData updateStatus(DepartmentReqDTO reqDTO) {
        ResponseData responseData;
        try {
            //原本数据是有效的状态
            if (reqDTO.getStatus() == 1) {
                //把数据变为无效的状态
                departmentDao.updateStatusToOFF(reqDTO.getDepId());
            } else {
                //把数据变为有效的状态
                departmentDao.updateStatusToON(reqDTO.getDepId());
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.UPDATE_DEPARTMENT_STATUS_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateDepartmentStatusFailedException(ExceptionCodeMessage.Code.UPDATE_DEPARTMENT_STATUS_FAILED,
                    ExceptionCodeMessage.Message.UPDATE_DEPARTMENT_STATUS_FAILED_MSG);
        }
        return responseData;
    }

    /**
     * 通过ID去查找
     *
     * @param depId
     * @return
     */
    @Override
    public DepartmentRespDTO queryById(Integer depId) {
        DepartmentRespDTO respDTO = departmentDao.queryById(depId);
        if (EmptyUtil.notEmpty(respDTO)) {
            if (respDTO.getStatus() == 1) {
                respDTO.setStatusDesc(Constants.EFFECTIVE_STATUS_DESC.getName());
            } else {
                respDTO.setStatusDesc(Constants.UN_EFFECTIVE_STATUS_DESC.getName());
            }
        }
        return respDTO;
    }

    /**
     * 添加部门
     *
     * @param departmentReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void addDepartment(DepartmentReqDTO departmentReqDTO) {
        try {
            if (EmptyUtil.notEmpty(departmentReqDTO)
                    && EmptyUtil.notEmpty(departmentReqDTO.getName())
                    && EmptyUtil.notEmpty(departmentReqDTO.getAddress())
                    && EmptyUtil.notEmpty(departmentReqDTO.getStatus())) {
                departmentDao.addDepartment(departmentReqDTO);
            } else {
                throw new DepartmentAddFailedException(ExceptionCodeMessage.Code.DEPARTMENT_ADD_FAILED,
                        ExceptionCodeMessage.Message.INFO_NOT_COMPLETE);
            }
        } catch (DepartmentAddFailedException e) {
            System.err.println(e.getMessage());
            throw new DepartmentAddFailedException(ExceptionCodeMessage.Code.DEPARTMENT_ADD_FAILED,
                    e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DepartmentAddFailedException(ExceptionCodeMessage.Code.DEPARTMENT_ADD_FAILED,
                    ExceptionCodeMessage.Message.DEPARTMENT_ADD_FAILED_MSG);
        }
    }

    /**
     * 修改部门
     *
     * @param departmentReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void updateDepartment(DepartmentReqDTO departmentReqDTO) {
        try {
            departmentDao.updateDepartment(departmentReqDTO);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DepartmentUpdateFailedException(ExceptionCodeMessage.Code.DEPARTMENT_UPDATE_FAILED,
                    ExceptionCodeMessage.Message.DEPARTMENT_UPDATE_FAILED_MSG);
        }
    }

    /**
     * 查询所有部门的信息(不用分页)
     *
     * @return
     */
    @Override
    public ResponseData queryAll() {
        ResponseData responseData;
        try {
            List<DepartmentRespDTO> respDTOS = departmentDao.queryAll();
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_DEPARTMENT_SUCCESS_MSG);
            //完善statusDesc字段
            for (DepartmentRespDTO respDTO : respDTOS) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc(Constants.EFFECTIVE_STATUS_DESC.getName());
                } else {
                    respDTO.setStatusDesc(Constants.UN_EFFECTIVE_STATUS_DESC.getName());
                }
            }
            responseData.putDataValue(Constants.DEPARTMENT_LIST_KEY_NAME.getName(), respDTOS);
        } catch (Exception e) {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, e.getMessage());
            responseData.putDataValue(Constants.DEPARTMENT_EXCEPTION.getName(), e);
        }
        return responseData;
    }

}

