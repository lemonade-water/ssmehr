package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Role;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

     //所有的角色·
    List<Role> queryAllRole();

    List<Role> queryRoleToMenu();

    List<Role> queryRoleByMenuId(long menuId);

    int addMenuToRole(long role_id, long menu_id);
}
