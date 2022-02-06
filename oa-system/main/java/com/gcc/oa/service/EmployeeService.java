package com.gcc.oa.service;

import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeReqDTO;
import com.gcc.oa.dto.response.EmployeeRespDTO;


/**
 * 员工表(Employee)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:29
 */
public interface EmployeeService {
    /**
     * 通过用户名和密码查询
     *
     * @param loginName
     * @param password
     * @return
     */
    EmployeeRespDTO queryByUsernameAndPwd(String loginName, String password);

    /**
     * 分页+条件查询
     *
     * @param employeeReqDTO
     * @return
     */
    ResponseData queryPageList(EmployeeReqDTO employeeReqDTO);

    /**
     * 修改状态
     *
     * @param reqDTO
     * @return
     */
    ResponseData updateStatus(EmployeeReqDTO reqDTO);

    /**
     * 通过ID进行查询
     *
     * @param emId
     * @return
     */
    EmployeeRespDTO queryById(Integer emId);

    /**
     * 添加员工
     *
     * @param employeeReqDTO
     */
    void addEmployee(EmployeeReqDTO employeeReqDTO);

    /**
     * 修改员工
     *
     * @param employeeReqDTO
     */
    void updateEmployee(EmployeeReqDTO employeeReqDTO);

    /**
     * 通过密码和用户名查找
     *
     * @param loginName
     * @return
     */
    String queryPasswordByUsername(String loginName);

    /**
     * 通过用户名查找
     *
     * @param loginName
     * @return
     */
    EmployeeRespDTO queryUserInfoByLoginName(String loginName);
}

