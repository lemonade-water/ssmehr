package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.unit.Message;

import java.util.List;

public interface RoleService {
    //查询出所有的角色
    List<Role> queryAllRole();

    List<Role> queryRoleToMenu();

    Message addMenutoRole(long role_id, long menu_id);
}
