package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.request.DepartmentReqDTO;
import com.gcc.oa.dto.response.DepartmentRespDTO;
import com.gcc.oa.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(Department)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
public interface DepartmentDao extends BaseMapper<Department> {
    /**
     * 根据ID查询
     *
     * @param depId
     * @return
     */
    DepartmentRespDTO queryById(int depId);

    /**
     * @param page      分页需要的分页对象
     * @param condition 条件
     * @return IPage
     */
    IPage<DepartmentRespDTO> queryPageList(Page<DepartmentRespDTO> page, @Param(Constants.WRAPPER) Wrapper<DepartmentReqDTO> condition);

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
     * @param departmentReqDTO
     */
    void addDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 修改部门
     *
     * @param departmentReqDTO
     */
    void updateDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 查询全部部门信息
     * @return
     */
    List<DepartmentRespDTO> queryAll();
}

