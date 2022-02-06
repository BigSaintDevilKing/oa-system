package com.gcc.oa.service;

import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.DepartmentReqDTO;
import com.gcc.oa.dto.response.DepartmentRespDTO;

/**
 * 部门表(Department)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
public interface DepartmentService {

    /**
     * 部门分页+条件查询
     *
     * @param departmentReqDTO
     * @return
     */
    ResponseData queryPageList(DepartmentReqDTO departmentReqDTO);

    /**
     * 更新状态
     *
     * @param reqDTO
     * @return
     */
    ResponseData updateStatus(DepartmentReqDTO reqDTO);

    /**
     * 根据ID查询
     *
     * @param depId
     * @return
     */
    DepartmentRespDTO queryById(Integer depId);

    /**
     * 添加
     *
     * @param departmentReqDTO
     */
    void addDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 修改
     *
     * @param departmentReqDTO
     */
    void updateDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 获取全部
     *
     * @return
     */
    ResponseData queryAll();
}

