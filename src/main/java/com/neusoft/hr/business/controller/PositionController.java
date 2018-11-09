package com.neusoft.hr.business.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.hr.business.entity.Position;
import com.neusoft.hr.business.service.PositionService;
import com.neusoft.hr.business.serviceImp.PositionServiceImp;
import com.neusoft.hr.sys.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */
@Controller
public class PositionController {

    @Autowired
    private PositionServiceImp positionServiceImp;




    @Permission("/position")
    @RequestMapping("/position")
    public ModelAndView findAllPosition(@RequestParam(required = true,defaultValue = "") String queryKey, @RequestParam(required = true,defaultValue = "1") int pageNum, ModelAndView modelAndView){
        if (queryKey==null||queryKey.equals("")){
            PageHelper.startPage(pageNum,5);
            List<Position> positions = positionServiceImp.queryAllPosition();
            PageInfo<Position> p = new PageInfo<>(positions);
            modelAndView.addObject("pageBean",p);
            modelAndView.addObject("positions",positions);
            //modelAndView.addObject("queryKey",queryKey);
        }else {
            PageHelper.startPage(pageNum,5);
            List<Position> positions = positionServiceImp.findPositionByName(queryKey);
            PageInfo<Position> p = new PageInfo<>(positions);
            modelAndView.addObject("pageBean",p);
            modelAndView.addObject("positions",positions);
        }

        modelAndView.setViewName("position");

        return modelAndView;
    }
    @Permission("/position")
    @RequestMapping("/deletePosition")
    public ModelAndView delete(Long id,ModelAndView modelAndView){
        positionServiceImp.delete(id);
        modelAndView.setViewName("forward:position");
        return modelAndView;
    }

    @ResponseBody
    @Permission("/position")
    @RequestMapping("/insertPosition")
    public ModelAndView insert(@RequestBody Position position,ModelAndView modelAndView){
        positionServiceImp.insert(position);
        modelAndView.setViewName("forward:position");
        return modelAndView;
    }
    @ResponseBody
    @Permission("/position")
    @RequestMapping("/updatePosition")
    public ModelAndView update(@RequestBody Position position,ModelAndView modelAndView){
        positionServiceImp.update(position);
        modelAndView.setViewName("forward:position");
        return modelAndView;
    }
}
