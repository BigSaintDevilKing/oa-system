package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.request.PositionReqDTO;
import com.gcc.oa.dto.response.PositionRespDTO;
import com.gcc.oa.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位表(Position)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
public interface PositionDao extends BaseMapper<Position> {

    /**
     * 根据ID进行查询
     *
     * @param posId
     * @return
     */
    PositionRespDTO queryById(int posId);

    /**
     * 分页+条件
     *
     * @param page      分页需要的分页对象
     * @param condition 条件
     * @return IPage
     */
    IPage<PositionRespDTO> queryPageList(Page<PositionRespDTO> page, @Param(Constants.WRAPPER) Wrapper<PositionReqDTO> condition);

    /**
     * 把部门的状态改为无效
     *
     * @param depId
     */
    void updateStatusToOFF(Integer depId);

    /**
     * 把部门的状态改为有效
     *
     * @param depId
     */
    void updateStatusToON(Integer depId);

    /**
     * 新增部门
     *
     * @param positionReqDTO
     */
    void addPosition(PositionReqDTO positionReqDTO);

    /**
     * 修改部门
     *
     * @param positionReqDTO
     */
    void updatePosition(PositionReqDTO positionReqDTO);

    /**
     * 不使用分页获取所有职位信息
     *
     * @return
     */
    List<PositionRespDTO> queryAll();
}

