package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.unit.Message;

import java.util.List;

public interface MenuService {

    List<Menu> queryAllList();

    Message addMenu(Menu menu);

    Message delMenu(long id);

    List<Role> queryRoleByMenuId(long menuId);

    List<Menu> queryMenuListByRoleId(long role_id);
}
