package com.gcc.oa.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.request.PermissionReqDTO;
import com.gcc.oa.dto.response.PermissionRespDTO;
import com.gcc.oa.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
public interface PermissionDao extends BaseMapper<Permission> {

    /**
     * 通过ID查询单条数据
     *
     * @param permissionId 主键
     * @return 实例对象
     */
    PermissionRespDTO queryById(Integer permissionId);


    /**
     * 统计总行数
     *
     * @param permission 查询条件
     * @return 总行数
     */
    long count(Permission permission);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(PermissionReqDTO permission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Permission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Permission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Permission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Permission> entities);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(PermissionReqDTO permission);

    /**
     * 通过主键删除数据
     *
     * @param permissionId 主键
     * @return 影响行数
     */
    int deleteById(Integer permissionId);

    /**
     * 条件+分页查询
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<PermissionRespDTO> queryPageList(Page<PermissionRespDTO> page, @Param(Constants.WRAPPER) QueryWrapper<PermissionReqDTO> condition);

    /**
     * 查询全部
     *
     * @return
     */
    List<PermissionRespDTO> queryAll();
}

