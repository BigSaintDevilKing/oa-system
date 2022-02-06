package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.request.RoleReqDTO;
import com.gcc.oa.dto.response.RoleRespDTO;
import com.gcc.oa.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-30 23:28:06
 */
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 分页+条件
     *
     * @param page      分页需要的分页对象
     * @param condition 条件
     * @return IPage
     */
    IPage<RoleRespDTO> queryPageList(Page<RoleRespDTO> page, @Param(Constants.WRAPPER) Wrapper<RoleReqDTO> condition);

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleRespDTO queryById(Integer roleId);


    /**
     * 统计总行数
     *
     * @param role 查询条件
     * @return 总行数
     */
    long count(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(RoleReqDTO role);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Role> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Role> entities);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(RoleReqDTO role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

    /**
     * 查询所有
     *
     * @return
     */
    List<RoleRespDTO> queryAll();


}

