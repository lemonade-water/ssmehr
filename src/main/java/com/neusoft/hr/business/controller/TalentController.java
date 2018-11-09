package com.neusoft.hr.business.controller;

import com.neusoft.hr.business.entity.Talent;
import com.neusoft.hr.business.serviceImp.DepartmentServiceImp;
import com.neusoft.hr.business.serviceImp.PositionServiceImp;
import com.neusoft.hr.business.serviceImp.TalentServiceImp;
import com.neusoft.hr.business.unit.PageBean;
import com.neusoft.hr.sys.annotation.Permission;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TalentController {
    
    @Autowired
    private TalentServiceImp talentServiceImp;
    @Autowired
   private DepartmentServiceImp departmentServiceImp;
   @Autowired
   private PositionServiceImp positionServiceImp;

    @Permission("/talent")
    @RequestMapping("/toTanlentPage")
    public String toTalent(){

        return "talent";
    }

    @Permission("/talent")
    @RequestMapping("/talent")
    public ModelAndView talent(@RequestParam(value = "query",required = false)String query,@RequestParam(value = "pages",required = false)Integer page){
        ModelAndView modelAndView=new ModelAndView();

        PageBean<Talent> pageBean=new PageBean<Talent>();
        if(page!=null){
            if(page!=0)
                pageBean.setPage(page);
        }
        if(query==null){
            query="";
        }
        else {
            pageBean.setQuery(query);
        }
        System.out.println(query);
        PageBean<Talent> talentPageBean = talentServiceImp.queryAllTalentLimit(pageBean, query);
        modelAndView.addObject("talentPageBean",talentPageBean);
        modelAndView.setViewName("forward:/toTanlentPage");
        modelAndView.addObject("talent",new Talent());
       modelAndView.addObject("departs",departmentServiceImp.queryAllDepartment(null));
       modelAndView.addObject("positions",positionServiceImp.queryAllPosition());

//        if(result.hasErrors()){
//            modelAndView.addObject("MSG", "出错啦！");
//        }else {
//            modelAndView.addObject("MSG", "提交成功！");
//        }
            return modelAndView;
    }

    @Permission("/talent")
    @RequestMapping("/updateTalent")
    public ModelAndView updateTalent(Talent talent){
        ModelAndView modelAndView = new ModelAndView();
        if(talent.getSexual().equals("1")){
            talent.setSexual("男");
        }else {
            talent.setSexual("女");
        }
        talentServiceImp.updateTalent(talent);
        modelAndView.setViewName("forward:/talent");
        return modelAndView;
    }

}
