package com.neusoft.hr.business.controller;

import com.neusoft.hr.business.entity.Role;
import com.neusoft.hr.business.entity.User;
import com.neusoft.hr.business.serviceImp.UserRoleService;
import com.neusoft.hr.business.serviceImp.UserServiceImp;
import com.neusoft.hr.sys.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private UserRoleService userRoleService;

    //返回user页面
    //@RequirePermission("admin")
    @Permission("/allUser")
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    //查询所有用户及用户角色

    @ResponseBody
    @Permission("/allUser")
    @RequestMapping("/allUser")
    public ModelAndView findAllUser(String pages, String queryKey, ModelAndView modelAndView){
        int page=0;//显示页面
        int count=0;//记录总数
        int totalpages=0;//页面总数
        int limit=10;//每页显示记录数
        if (queryKey==null||queryKey.equals("")){
            count = userService.queryCount();
        }else{
            count = userService.queryCountByName(queryKey);
        }
        totalpages=(int) Math.ceil(count/(limit*1.0));//页面总数
        if (pages == null) {
            page = 1;
        } else {
            try{
                page = Integer.parseInt(pages);//异常字符
            }catch(Exception e){
                page = 1;
            }if (page < 1){
                page = 1;
            }if (page > totalpages){//大于总页数
                page = totalpages;
            }
        }
        List<User> userList = new ArrayList<>();
        if(queryKey==null||queryKey.equals("")){
            userList=userService.queryAllUser(page,limit);
            modelAndView.addObject("userList",userList);
            queryKey="";
        }else {
            userList=userService.queryAllUserByName(page,limit,queryKey);
            modelAndView.addObject("userList",userList);
        }
        modelAndView.addObject("queryKey",queryKey);

        List<Role> roles = new ArrayList<>();
        roles=userRoleService.findAllRoles();
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("page",page);
        modelAndView.addObject("totalpages",totalpages);

        /*modelAndView.setViewName("forward:/user");*/
        modelAndView.setViewName("user");
        return modelAndView;
    }

    //修改User
    @Permission("/allUser")
    @ResponseBody
    @RequestMapping("/updateUser")
    public ModelAndView update(@RequestBody User user,ModelAndView modelAndView) {
        userService.update(user);
        modelAndView.setViewName("forward:/allUser");
        return modelAndView;
    }

    //新增user
    @Permission("/allUser")
    @ResponseBody
    @RequestMapping("/insertUser")
    public ModelAndView insert(@RequestBody User user,ModelAndView modelAndView){
        Date time = new Date();
        user.setCreateTime(time);//创建当前时间加入user对象

        userService.insert(user);
        modelAndView.setViewName("forward:/allUser");
        return modelAndView;
    }
    //删除user
    @Permission("/allUser")
    @ResponseBody
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(Long id,ModelAndView modelAndView){
        userService.deleteUser(id);
        modelAndView.setViewName("forward:/allUser");
        return modelAndView;
    }

    //删除用户角色
    @Permission("/allUser")
    @ResponseBody
    @RequestMapping("/deleteUserRole")
    public ModelAndView deleteUserRole(Long user_id,Long role_id,ModelAndView modelAndView){
        userRoleService.deleteUserRole(user_id,role_id);
        modelAndView.setViewName("forward:/allUser");
        return modelAndView;
    }
    //新增用户角色
    @Permission("/allUser")
    @ResponseBody
    @RequestMapping("/insertUserRole")
    public ModelAndView insertUserRole(Long user_id,Long role_id,ModelAndView modelAndView){
        userRoleService.insertUserRole(user_id,role_id);
        modelAndView.setViewName("forward:/allUser");
        return modelAndView;
    }



}
