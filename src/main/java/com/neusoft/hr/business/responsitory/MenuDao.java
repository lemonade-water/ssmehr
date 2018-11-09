package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    List<Menu> queryAllMenu();

    int addMenu(Menu menu);

    int delMenu(long id);

    List<Menu> queryMenuListByRoleId(long role_id);
}
