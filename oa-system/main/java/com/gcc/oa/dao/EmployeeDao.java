package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.request.EmployeeReqDTO;
import com.gcc.oa.dto.response.EmployeeRespDTO;
import com.gcc.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工表(Employee)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:28
 */
public interface EmployeeDao extends BaseMapper<Employee> {
    EmployeeRespDTO queryByUsernameAndPwd(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * @param page      分页需要的分页对象
     * @param condition 条件
     * @return IPage
     */
    IPage<EmployeeRespDTO> queryPageList(Page<EmployeeRespDTO> page, @Param(Constants.WRAPPER) Wrapper<EmployeeReqDTO> condition);

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
     * @param employeeReqDTO
     */
    void addEmployee(EmployeeReqDTO employeeReqDTO);

    /**
     * 修改部门
     *
     * @param employeeReqDTO
     */
    void updateEmployee(EmployeeReqDTO employeeReqDTO);

    /**
     * 根据员工ID进行查询
     *
     * @param emId
     * @return
     */
    EmployeeRespDTO queryById(int emId);

    /**
     * 根据登录的账号来查询
     *
     * @param loginName
     * @return
     */
    List<EmployeeRespDTO> queryByLoginName(String loginName);

    /**
     * 根据用户名查密码
     *
     * @param loginName
     * @return
     */
    String queryPasswordByUsername(String loginName);

    /**
     * 返回用户信息
     *
     * @param loginName
     * @return
     */
    EmployeeRespDTO queryUserInfoByLoginName(String loginName);
}

