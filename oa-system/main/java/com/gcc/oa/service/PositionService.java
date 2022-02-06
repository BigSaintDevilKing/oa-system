package com.gcc.oa.service;

import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PositionReqDTO;
import com.gcc.oa.dto.response.PositionRespDTO;

/**
 * 职位表(Position)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
public interface PositionService {
    /**
     * 分页+条件查询
     *
     * @param positionReqDTO
     * @return
     */
    ResponseData queryPageList(PositionReqDTO positionReqDTO);

    /**
     * 更新状态
     *
     * @param reqDTO
     * @return
     */
    ResponseData updateStatus(PositionReqDTO reqDTO);

    /**
     * 根据ID进行查询
     *
     * @param depId
     * @return
     */
    PositionRespDTO queryById(Integer depId);

    /**
     * 添加
     *
     * @param positionReqDTO
     */
    void addPosition(PositionReqDTO positionReqDTO);

    /**
     * 修改
     *
     * @param positionReqDTO
     */
    void updatePosition(PositionReqDTO positionReqDTO);

    /**
     * 获取全部
     *
     * @return
     */
    ResponseData queryAll();
}

