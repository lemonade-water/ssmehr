package com.neusoft.hr.business.controller;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.serviceImp.MenuServiceImp;
import com.neusoft.hr.business.serviceImp.RoleServiceImp;
import com.neusoft.hr.business.unit.Message;
import com.neusoft.hr.sys.annotation.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuServiceImp menuServiceImp;
    @Autowired
    private RoleServiceImp roleServiceImp;
    //menu
    @Permission("/menu")
    @RequestMapping("/toMenu")
    public String toMenu(){
        return "menu";
    }

    @Permission("/menu")
    @RequestMapping("/menu")
    public ModelAndView menu(ModelAndView modelAndViewd){
        //这里是数据
        //查询所有的menu
        modelAndViewd.addObject("menus",menuServiceImp.queryAllList());
        List<Role> roles = roleServiceImp.queryRoleToMenu();
//        for (Role role:roles){
//            List<Menu> menuList = role.getMenuList();
//            menuList.size();
//        }
        modelAndViewd.addObject("roles",roles);
        modelAndViewd.setViewName("forward:/toMenu");
        return modelAndViewd;
    }

    @ResponseBody
    @Permission("/menu")
    @RequestMapping("/ajaxAddMenu")
    public Message ajaxAddMenu(Menu menu){
        //System.out.println(menu);
        Message message=menuServiceImp.addMenu(menu);
        return message;
    }


    @ResponseBody
    @Permission("/menu")
    @RequestMapping("/ajaxDelMenu")
    public Message ajaxDelMenu(@RequestParam("delete_id")long id){
        Message message = menuServiceImp.delMenu(id);
        return message;
    }

    @ResponseBody
    @Permission("/menu")
    @RequestMapping("/ajaxQueRole")
    public Object ajaxQueRole(@RequestParam("query_id")long menuId){
        List<Role> roles = menuServiceImp.queryRoleByMenuId(menuId);
        return roles;
    }

    @ResponseBody
    @Permission("/menu")
    @RequestMapping("/addMenutoRole")
    public Message addMenutoRole(@RequestParam("role_id") long role_id,@RequestParam("menu_id")long menu_id,HttpServletRequest request){
        Message message =roleServiceImp.addMenutoRole(role_id,menu_id);
        if(message.getMesId()==200){
            //替换session
            HttpSession session = request.getSession(false);
            String type = (String) session.getAttribute("type");


            if(type.equals(String.valueOf(role_id))){
                List<Menu> list=menuServiceImp.queryMenuListByRoleId(role_id);
                session.setAttribute("menu",list);
            }
        }
        return message;
    }
}
