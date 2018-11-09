package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.User;
import com.neusoft.hr.business.responsitory.UserDao;
import com.neusoft.hr.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(HttpServletResponse response, User user, String type) {
        User login = userDao.login(user.getUsername(), user.getPassword(), type);
        if(login!=null){
            return login;
        }
        return null;
    }

    public User find(Long id) {
        return userDao.find(id);
    }

    public List<User> findList(User condition) {
        return userDao.findList(condition);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    //查找菜单
    public List<Menu> findListMenu(User condition){
        return userDao.findListMenu(condition);
    }

    //查找所有记录总数
    public int queryCount() {
        return userDao.queryCount();
    }
    //条件查询记录总数
    public int queryCountByName(String name) {
        return userDao.queryCountByName(name);
    }
    //查询所有用户及角色
    public List<User> queryAllUser(int page, int limit) {
        page = (page-1)*limit;
        return userDao.queryAllUser(page,limit);
    }
    //条件查询
    public List<User> queryAllUserByName(int page, int limit, String name) {
        page = (page-1)*limit;
        return userDao.queryAllUserByName(page,limit,name);
    }

    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }
}
