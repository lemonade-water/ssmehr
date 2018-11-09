package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */
@Repository
public interface UserRoleDao {

    List<Role> findRoles(@Param("id") Long id);

    List<Role> findAllRoles();

    void deleteUserRole(Long user_id, Long role_id);

    void insertUserRole(Long user_id, Long role_id);
}
