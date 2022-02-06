package com.gcc.oa.service.impl;

import com.gcc.oa.dao.EmployeeRoleDao;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.EmployeeRoleReqDTO;
import com.gcc.oa.dto.response.EmployeeRoleRespDTO;
import com.gcc.oa.service.EmployeeRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

/**
 * 员工与角色关系表(EmployeeRole)表服务实现类
 *
 * @author 小李探花
 * @since 2022-01-31 22:47:40
 */
@Service("employeeRoleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
    @Resource
    private EmployeeRoleDao employeeRoleDao;


    @Override
    public ResponseData batchInsert(EmployeeRoleReqDTO elements) {
        try {
            employeeRoleDao.insertBatch(elements);
            return ResponseData.ok("员工与角色关系添加成功");
        } catch (Exception e) {
            System.out.println("员工与角色关系添加失败: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData deleteById(Integer emRoleId) {
        try {
            employeeRoleDao.deleteByEmployeeId(emRoleId);
            return ResponseData.ok("员工与角色关系删除成功");
        } catch (Exception e) {
            System.out.println("员工与角色关系删除失败: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseData queryById(Integer emId) {
        try {
            List<Integer> assignRoles = employeeRoleDao.queryByEmployeeId(emId);
            ResponseData responseData = ResponseData.ok("员工与角色关系查询成功");
            responseData.putDataValue("assignRoles", assignRoles);
            return responseData;
        } catch (Exception e) {
            ResponseData responseData = ResponseData.error(e.getMessage());
            responseData.putException(e);
            return responseData;
        }
    }

    @Override
    public List<EmployeeRoleRespDTO> queryModelByEmId(Integer emId) {
        return employeeRoleDao.queryModelByEmployeeId(emId);
    }
}
