package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.unit.ChartBean;

import java.util.List;
import java.util.Map;

public interface ChartService {
    List<ChartBean> departmentChart();
    List<ChartBean> positionChart();
}
