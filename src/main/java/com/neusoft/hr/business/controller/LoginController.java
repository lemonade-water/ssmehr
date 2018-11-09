package com.neusoft.hr.business.controller;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.entity.User;
import com.neusoft.hr.business.serviceImp.RoleServiceImp;
import com.neusoft.hr.business.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController{

    @Autowired
    private RoleServiceImp roleServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/")
    public String toLoginPage(Model model){
        // 查询出所有的角色
        List<Role> roles = roleServiceImp.queryAllRole();
        model.addAttribute("roleList",roles);
        return "login";
    }
    //登录
    @RequestMapping("/login")
    public String login(User user,HttpServletRequest request,HttpServletResponse response,Model model){
        String type = request.getParameter("role");
        User login = userServiceImp.login(response, user, type);
        List<Menu> menus=new ArrayList<Menu>();
        HttpSession session = request.getSession();
        if(login!=null){
            session.setAttribute("principal",login);
            session.setAttribute("type",type);
            for (Role role:login.getRoleList()){
                for(Menu menu:role.getMenuList()){
                    menus.add(menu);
                }
            }
        }
        session.setAttribute("menu",menus);

        return "index";
    }
}
