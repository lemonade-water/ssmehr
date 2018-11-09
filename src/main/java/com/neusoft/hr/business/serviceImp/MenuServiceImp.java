package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.responsitory.MenuDao;
import com.neusoft.hr.business.responsitory.RoleDao;
import com.neusoft.hr.business.service.MenuService;
import com.neusoft.hr.business.unit.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Menu> queryAllList() {
        return menuDao.queryAllMenu();
    }

    @Override
    public Message addMenu(Menu menu) {
        Message message = new Message();
        if (menuDao.addMenu(menu) > 0) {
            message.setMesId(200);
            message.setMesName("增加成功！");
        } else {
            message.setMesId(404);
            message.setMesName("增加失败！");
        }
        return message;
    }

    @Override
    public Message delMenu(long id) {
        Message message = new Message();
        try {
            if (menuDao.delMenu(id) > 0) {
                message.setMesId(200);
                message.setMesName("删除成功！");
            } else {
                message.setMesId(404);
                message.setMesName("删除失败！");
            }
        } catch (Exception e) {
            message.setMesId(500);
            message.setMesName("请先删除用户的角色！");
        }
        return message;
    }

    @Override
    public List<Role> queryRoleByMenuId(long menuId) {

        return roleDao.queryRoleByMenuId(menuId);
    }

    @Override
    public List<Menu> queryMenuListByRoleId(long role_id) {
        return menuDao.queryMenuListByRoleId(role_id);
    }
}
