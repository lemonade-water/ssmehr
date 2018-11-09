package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentDao {

    List<Department> queryAllDepartment(String name);
}
