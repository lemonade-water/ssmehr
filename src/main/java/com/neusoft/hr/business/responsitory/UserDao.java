package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    User login(String username,String password,String type);

    int queryCount();

    int queryCountByName(@Param("name") String name);

    List<User> queryAllUser(int page, int limit);

   List<User> queryAllUserByName(@Param("page") int page, @Param("limit") int limit, @Param("name") String name);
//List<User> queryAllUserByName(int page,int limit,String name);

    void deleteUser(@Param("id") Long id);
    /**
     * 通过ID查找user
     * @param id
     * @return
     */
    public User find(Long id);

    /**
     * t通过条件查询
     * @param condition
     * @return
     */
    List<User> findList(User condition);

    //查找菜单
    List<Menu> findListMenu(User condition);

    /**
     * 插入方法
     * @param user
     */
    void insert(User user);

    void update(User user);
}
