package com.neusoft.hr.business.serviceImp;


import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.responsitory.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    //查询用户角色
    public List<Role> findRoles(Long id) {
        return userRoleDao.findRoles(id);

    }

    //查询所有角色
    public List<Role> findAllRoles() {
        return userRoleDao.findAllRoles();

    }
    //删除用户角色
    public void deleteUserRole(Long user_id, Long role_id) {
        userRoleDao.deleteUserRole(user_id,role_id);
    }

    public void insertUserRole(Long user_id, Long role_id) {
        userRoleDao.insertUserRole(user_id,role_id);
    }
}
