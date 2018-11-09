package com.neusoft.hr.business.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.serviceImp.DepartmentServiceImp;
import com.neusoft.hr.sys.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServiceImp departmentServiceImp;

    @Permission("/department")
    @RequestMapping("/toDepartment")
    public String toDepartment(){

        return "depart";
    }
    @Permission("/department")
    @RequestMapping("/department")
    public ModelAndView depart(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("forward:/toDepartment");

        List<Department> list = departmentServiceImp.queryAllDepartment(null);

        PageInfo pageInfo = new PageInfo<>(list,5);
        PageHelper.startPage(pn,5);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

}
