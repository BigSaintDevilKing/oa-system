package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.oa.dto.request.EmployeeRoleReqDTO;
import com.gcc.oa.dto.response.EmployeeRoleRespDTO;
import com.gcc.oa.entity.EmployeeRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工与角色关系表(EmployeeRole)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-31 23:16:20
 */
public interface EmployeeRoleDao extends BaseMapper<EmployeeRole> {

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    List<Integer> queryByEmployeeId(Integer emId);


    /**
     * 统计总行数
     *
     * @param employeeRole 查询条件
     * @return 总行数
     */
    long count(EmployeeRole employeeRole);

    /**
     * 新增数据
     *
     * @param employeeRole 实例对象
     * @return 影响行数
     */
    int insert(EmployeeRoleReqDTO employeeRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmployeeRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(EmployeeRoleReqDTO entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmployeeRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<EmployeeRole> entities);

    /**
     * 修改数据
     *
     * @param employeeRole 实例对象
     * @return 影响行数
     */
    int update(EmployeeRole employeeRole);

    /**
     * 通过主键删除数据
     *
     * @param emRoleId 主键
     * @return 影响行数
     */
    int deleteByEmployeeId(Integer emRoleId);

    /**
     * 根据employeeId来查找
     *
     * @param emId
     * @return
     */
    List<EmployeeRoleRespDTO> queryModelByEmployeeId(Integer emId);
}

