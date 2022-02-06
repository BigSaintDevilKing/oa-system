package com.gcc.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.RoleReqDTO;
import com.gcc.oa.dao.RoleDao;
import com.gcc.oa.dto.response.RoleRespDTO;
import com.gcc.oa.service.RoleService;
import com.gcc.oa.util.EmptyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-30 19:51:50
 */
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public ResponseData add(RoleReqDTO reqDTO) {
        try {
            if (EmptyUtil.notEmpty(reqDTO)
                    && EmptyUtil.notEmpty(reqDTO.getRoleName())
                    && EmptyUtil.notEmpty(reqDTO.getRoleDesc())
                    && EmptyUtil.notEmpty(reqDTO.getStatus())) {
                roleDao.insert(reqDTO);
                return ResponseData.ok("角色添加成功");
            } else {
                throw new RuntimeException("请填写完整信息!");
            }
        } catch (Exception e) {
            System.out.println("添加角色时出现的异常: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public ResponseData queryPageList(RoleReqDTO reqDTO) {
        ResponseData responseData;
        try {
            QueryWrapper<RoleReqDTO> condition = new QueryWrapper<>();
            Page<RoleRespDTO> page = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());
            if (EmptyUtil.notEmpty(reqDTO.getRoleName())) {
                condition.like("role_name", reqDTO.getRoleName());
            }
            if (EmptyUtil.notEmpty(reqDTO.getStatus())) {
                condition.eq("status", reqDTO.getStatus());
            }
            IPage<RoleRespDTO> roles = roleDao.queryPageList(page, condition);
            for (RoleRespDTO record : roles.getRecords()) {
                if (record.getStatus() == 1) {
                    record.setStatusDesc("有效");
                } else {
                    record.setStatusDesc("无效");
                }
            }
            responseData = ResponseData.ok("角色信息查询成功");
            responseData.putDataValue("roles", roles);
        } catch (Exception e) {
            responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
        }
        return responseData;
    }

    @Override
    public ResponseData updateRole(RoleReqDTO roleReqDTO) {
        try {
            roleDao.update(roleReqDTO);
            return ResponseData.ok("角色修改成功");
        } catch (Exception e) {
            System.out.println("修改角色时出现的异常: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData getAll() {
        ResponseData responseData;
        try {
            List<RoleRespDTO> result = roleDao.queryAll();
            responseData = ResponseData.ok("角色信息查询成功");
            responseData.putDataValue("roles", result);
            for (RoleRespDTO respDTO : result) {
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
