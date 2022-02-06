package com.gcc.oa.service;


import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeRoleReqDTO;
import com.gcc.oa.dto.response.EmployeeRoleRespDTO;

import java.util.List;

/**
 * 员工与角色关系表(EmployeeRole)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-31 22:47:40
 */
public interface EmployeeRoleService {
    /**
     * 批量新增
     *
     * @param elements
     * @return
     */
    ResponseData batchInsert(EmployeeRoleReqDTO elements);

    /**
     * 删除
     *
     * @param emRoleId
     * @return
     */
    ResponseData deleteById(Integer emRoleId);

    /**
     * 根据ID进行查找
     *
     * @param emId
     * @return
     */
    ResponseData queryById(Integer emId);

    /**
     * 更具员工名称查找
     *
     * @param emId
     * @return
     */
    List<EmployeeRoleRespDTO> queryModelByEmId(Integer emId);
}
