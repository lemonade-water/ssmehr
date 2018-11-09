package com.neusoft.hr.business.controller;

import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.serviceImp.ChartServiceImp;
import com.neusoft.hr.business.unit.ChartBean;
import com.neusoft.hr.sys.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EchartController {
    @Autowired
    private ChartServiceImp chartServiceImp;

    @Permission("/echart")
    @RequestMapping("/echart")
    public String echart(){
        return "echart";
    }

    @Permission("/echart")
    @ResponseBody
    @RequestMapping("/data")
    public Object data(){
        List<ChartBean> departmentChart = chartServiceImp.departmentChart();
        List<ChartBean> positionChart = chartServiceImp.positionChart();
        Map<String,Object> map=new HashMap<>();
        map.put("department",departmentChart);
        map.put("position",positionChart);
        return map;
    }

}
