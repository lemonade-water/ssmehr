package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    //login
    User login(HttpServletResponse response, User user, String type);
}
