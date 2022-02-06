package com.gcc.oa.dao;

import com.gcc.oa.dto.request.RolePermissionReqDTO;
import com.gcc.oa.dto.response.RolePermissionRespDTO;
import com.gcc.oa.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与权限关系表(RolePermission)表数据库访问层
 *
 * @author 小李探花
 * @since 2022-01-31 23:16:06
 */
public interface RolePermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    List<Integer> queryByRoleId(Integer roleId);

    /**
     * 统计总行数
     *
     * @param rolePermission 查询条件
     * @return 总行数
     */
    long count(RolePermission rolePermission);

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int insert(RolePermission rolePermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(RolePermissionReqDTO entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RolePermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RolePermission> entities);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int update(RolePermission rolePermission);

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 影响行数
     */
    int deleteByRoleId(Integer rolePermissionId);

    /**
     * 通过角色Id查找
     *
     * @param roleId
     * @return
     */
    List<RolePermissionRespDTO> queryModelByRoleId(Integer roleId);
}

