package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.responsitory.DepartmentDao;
import com.neusoft.hr.business.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> queryAllDepartment(String name) {
        return departmentDao.queryAllDepartment(name);
    }
}
