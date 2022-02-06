package com.gcc.oa.service;

import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RolePermissionReqDTO;
import com.gcc.oa.dto.response.RolePermissionRespDTO;

import java.util.List;

/**
 * 角色与权限关系表(RolePermission)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-31 22:48:03
 */
public interface RolePermissionService {
    /**
     * 批量新增
     *
     * @param elements
     * @return
     */
    ResponseData batchInsert(RolePermissionReqDTO elements);

    /**
     * 删除
     *
     * @param rolePermissionId
     * @return
     */
    ResponseData deleteByRoleId(Integer rolePermissionId);

    /**
     * 通过RoleId查找所拥有的permissionId
     *
     * @param emId
     * @return
     */
    ResponseData queryByRoleId(Integer emId);

    /**
     * 根据角色Id查找
     *
     * @param roleId
     * @return
     */
    List<RolePermissionRespDTO> queryModelByRoleId(Integer roleId);
}
