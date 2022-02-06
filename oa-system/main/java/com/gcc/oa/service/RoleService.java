package com.gcc.oa.service;


import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RoleReqDTO;

/**
 * 角色表(RoleReqDTO)表服务接口
 *
 * @author 小李探花
 * @since 2022-01-30 19:51:50
 */
public interface RoleService {
    /**
     * 添加角色
     *
     * @param reqDTO
     * @return
     */
    ResponseData add(RoleReqDTO reqDTO);

    /**
     * 分页+条件查询
     *
     * @param reqDTO
     * @return
     */
    ResponseData queryPageList(RoleReqDTO reqDTO);

    /**
     * 修改角色
     *
     * @param roleReqDTO
     * @return
     */
    ResponseData updateRole(RoleReqDTO roleReqDTO);

    /**
     * 获取所有的角色信息
     *
     * @return
     */
    ResponseData getAll();
}
