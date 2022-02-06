package com.gcc.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dao.PermissionDao;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PermissionReqDTO;
import com.gcc.oa.dto.response.PermissionRespDTO;
import com.gcc.oa.service.PermissionService;
import com.gcc.oa.util.EmptyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(Permission)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-31 15:03:11
 */
@Service("permissionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;


    @Override
    public ResponseData add(PermissionReqDTO reqDTO) {
        try {
            if (EmptyUtil.notEmpty(reqDTO)
                    && EmptyUtil.notEmpty(reqDTO.getPermissionName())
                    && EmptyUtil.notEmpty(reqDTO.getPermissionDesc())
                    && EmptyUtil.notEmpty(reqDTO.getStatus())) {
                permissionDao.insert(reqDTO);
                return ResponseData.ok("权限添加成功");
            } else {
                throw new RuntimeException("请填写完整信息!");
            }
        } catch (Exception e) {
            System.out.println("添加权限时出现的异常: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public ResponseData queryPageList(PermissionReqDTO reqDTO) {
        ResponseData responseData;
        try {
            QueryWrapper<PermissionReqDTO> condition = new QueryWrapper<>();
            Page<PermissionRespDTO> page = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());
            if (EmptyUtil.notEmpty(reqDTO.getPermissionName())) {
                condition.like("permission_name", reqDTO.getPermissionName());
            }
            if (EmptyUtil.notEmpty(reqDTO.getStatus())) {
                condition.eq("status", reqDTO.getStatus());
            }
            IPage<PermissionRespDTO> permissions = permissionDao.queryPageList(page, condition);
            for (PermissionRespDTO record : permissions.getRecords()) {
                if (record.getStatus() == 1) {
                    record.setStatusDesc("有效");
                } else {
                    record.setStatusDesc("无效");
                }
            }
            responseData = ResponseData.ok("权限信息查询成功");
            responseData.putDataValue("permissions", permissions);
        } catch (Exception e) {
            responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
        }
        return responseData;
    }

    @Override
    public ResponseData updatePermission(PermissionReqDTO permissionReqDTO) {
        try {
            permissionDao.update(permissionReqDTO);
            return ResponseData.ok("权限修改成功");
        } catch (Exception e) {
            System.out.println("修改权限时出现的异常: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData getAll() {
        ResponseData responseData;
        try {
            List<PermissionRespDTO> result = permissionDao.queryAll();
            responseData = ResponseData.ok("权限信息查询成功");
            responseData.putDataValue("permissions", result);
            for (PermissionRespDTO respDTO : result) {
                if (respDTO.getStatus() == 1) {
                    respDTO.setStatusDesc("有效");
                } else {
                    respDTO.setStatusDesc("无效");
                }
            }
        } catch (Exception e) {
            responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
        }
        return responseData;
    }
}
