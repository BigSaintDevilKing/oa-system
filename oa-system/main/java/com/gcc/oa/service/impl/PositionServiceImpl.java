package com.gcc.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dao.PositionDao;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PositionReqDTO;
import com.gcc.oa.dto.response.PositionRespDTO;
import com.gcc.oa.error.position.PositionAddFailedException;
import com.gcc.oa.error.position.PositionUpdateFailedException;
import com.gcc.oa.error.position.UpdatePositionStatusFailedException;
import com.gcc.oa.service.PositionService;
import com.gcc.oa.util.EmptyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位表(Position)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    /**
     * 分页+条件查询
     *
     * @param positionReqDTO
     * @return
     */
    @Override
    public ResponseData queryPageList(PositionReqDTO positionReqDTO) {
        ResponseData responseData;
        try {
            QueryWrapper<PositionReqDTO> condition = new QueryWrapper<>();
            if (EmptyUtil.notEmpty(positionReqDTO.getPositionName())) {
                condition.like("position_name", positionReqDTO.getPositionName());
            }
            if (EmptyUtil.notEmpty(positionReqDTO.getStatus())) {
                condition.eq("status", positionReqDTO.getStatus());
            }
            Page<PositionRespDTO> page = new Page<>(positionReqDTO.getCurrent(), positionReqDTO.getSize());
            IPage<PositionRespDTO> departments = positionDao.queryPageList(page, condition);
            //完善statusDesc字段
            for (PositionRespDTO respDTO : departments.getRecords()) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc(Constants.EFFECTIVE_STATUS_DESC.getName());
                } else {
                    respDTO.setStatusDesc(Constants.UN_EFFECTIVE_STATUS_DESC.getName());
                }
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_POSITION_SUCCESS_MSG);
            responseData.putDataValue(Constants.POSITION_LIST_KEY_NAME.getName(), departments);
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
    public ResponseData updateStatus(PositionReqDTO reqDTO) {
        ResponseData responseData;
        try {
            //原本数据是有效的状态
            if (reqDTO.getStatus() == 1) {
                //把数据变为无效的状态
                positionDao.updateStatusToOFF(reqDTO.getPositionId());
            } else {
                //把数据变为有效的状态
                positionDao.updateStatusToON(reqDTO.getPositionId());
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.UPDATE_POSITION_STATUS_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdatePositionStatusFailedException(ExceptionCodeMessage.Code.UPDATE_POSITION_STATUS_FAILED,
                    ExceptionCodeMessage.Message.UPDATE_POSITION_STATUS_FAILED_MSG);
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
    public PositionRespDTO queryById(Integer depId) {
        PositionRespDTO respDTO = positionDao.queryById(depId);
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
     * @param positionReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void addPosition(PositionReqDTO positionReqDTO) {
        try {
            if (EmptyUtil.notEmpty(positionReqDTO)
                    && EmptyUtil.notEmpty(positionReqDTO.getPositionName())
                    && EmptyUtil.notEmpty(positionReqDTO.getStatus())) {
                positionDao.addPosition(positionReqDTO);
            } else {
                throw new PositionAddFailedException(ExceptionCodeMessage.Code.POSITION_ADD_FAILED,
                        ExceptionCodeMessage.Message.INFO_NOT_COMPLETE);
            }
        } catch (PositionAddFailedException e) {
            throw new PositionAddFailedException(ExceptionCodeMessage.Code.POSITION_ADD_FAILED,
                    e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new PositionAddFailedException(ExceptionCodeMessage.Code.POSITION_ADD_FAILED,
                    ExceptionCodeMessage.Message.POSITION_ADD_FAILED_MSG);
        }
    }

    /**
     * 修改部门
     *
     * @param positionReqDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public void updatePosition(PositionReqDTO positionReqDTO) {
        try {
            positionDao.updatePosition(positionReqDTO);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new PositionUpdateFailedException(ExceptionCodeMessage.Code.POSITION_UPDATE_FAILED,
                    ExceptionCodeMessage.Message.POSITION_UPDATE_FAILED_MSG);
        }
    }

    /**
     * 不使用分页获取所有职位信息
     *
     * @return
     */
    @Override
    public ResponseData queryAll() {
        ResponseData responseData;
        try {
            List<PositionRespDTO> respDTOS = positionDao.queryAll();
            for (PositionRespDTO respDTO : respDTOS) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc(Constants.EFFECTIVE_STATUS_DESC.getName());
                } else {
                    respDTO.setStatusDesc(Constants.UN_EFFECTIVE_STATUS_DESC.getName());
                }
            }
            responseData = ResponseData.ok(ExceptionCodeMessage.Message.QUERY_POSITION_SUCCESS_MSG);
            responseData.putDataValue(Constants.POSITION_LIST_KEY_NAME.getName(), respDTOS);
        } catch (Exception e) {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, e.getMessage());
            responseData.putDataValue(Constants.POSITION_EXCEPTION.getName(), e);
        }
        return responseData;
    }
}

