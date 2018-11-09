package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> queryAllDepartment(String name);
}
