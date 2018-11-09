package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.responsitory.RoleDao;
import com.neusoft.hr.business.service.RoleService;
import com.neusoft.hr.business.unit.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> queryAllRole() {
        return roleDao.queryAllRole();
    }

    @Override
    public List<Role> queryRoleToMenu() {
        List<Role> roles = roleDao.queryRoleToMenu();
        return roles;
    }

    @Override
    public Message addMenutoRole(long role_id, long menu_id) {
        Message message = new Message();
        try {
            if (roleDao.addMenuToRole(role_id, menu_id) > 0) {
                message.setMesId(200);
                message.setMesName("给角色添加菜单成功！");
            } else {
                message.setMesId(400);
                message.setMesName("给角色添加菜单失败！");
            }
        } catch (Exception e) {
            message.setMesId(400);
            message.setMesName("该角色已有此菜单！");
        }
        return message;
    }
}
