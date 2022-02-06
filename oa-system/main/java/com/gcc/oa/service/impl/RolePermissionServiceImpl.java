package com.gcc.oa.service.impl;

import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RolePermissionReqDTO;
import com.gcc.oa.dao.RolePermissionDao;
import com.gcc.oa.dto.response.RolePermissionRespDTO;
import com.gcc.oa.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限与权限关系表(RolePermission)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-31 22:48:03
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionDao rolePermissionDao;


    @Override
    public ResponseData batchInsert(RolePermissionReqDTO elements) {
        try {
            rolePermissionDao.insertBatch(elements);
            return ResponseData.ok("角色与权限关系添加成功");
        } catch (Exception e) {
            System.out.println("角色与权限关系批量添加失败: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData deleteByRoleId(Integer roleId) {
        try {
            rolePermissionDao.deleteByRoleId(roleId);
            return ResponseData.ok("角色与权限关系删除成功");
        } catch (Exception e) {
            System.out.println("角色与权限关系删除失败: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData queryByRoleId(Integer roleId) {
        try {
            List<Integer> assignPermissions = rolePermissionDao.queryByRoleId(roleId);
            ResponseData responseData = ResponseData.ok("角色与权限关系查询成功");
            responseData.putDataValue("assignPermissions", assignPermissions);
            return responseData;
        } catch (Exception e) {
            ResponseData responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
            return responseData;
        }
    }

    @Override
    public List<RolePermissionRespDTO> queryModelByRoleId(Integer roleId) {
        return rolePermissionDao.queryModelByRoleId(roleId);
    }
}
