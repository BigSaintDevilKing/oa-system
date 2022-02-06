package com.gcc.oa.service;


import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PermissionReqDTO;

/**
 * 权限表(Permission)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
public interface PermissionService {

    /**
     * 分页+条件查询
     *
     * @param reqDTO
     * @return
     */
    ResponseData queryPageList(PermissionReqDTO reqDTO);

    /**
     * 更新权限
     *
     * @param roleReqDTO
     * @return
     */
    ResponseData updatePermission(PermissionReqDTO roleReqDTO);

    /**
     * 添加权限
     *
     * @param roleReqDTO
     * @return
     */
    ResponseData add(PermissionReqDTO roleReqDTO);

    /**
     * 获取所有权限
     *
     * @return
     */
    ResponseData getAll();
}
