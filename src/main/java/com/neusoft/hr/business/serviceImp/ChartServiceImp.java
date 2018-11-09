package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.responsitory.ChartDao;
import com.neusoft.hr.business.service.ChartService;
import com.neusoft.hr.business.unit.ChartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImp implements ChartService {

    @Autowired
    private ChartDao chartDao;

    @Override
    public List<ChartBean> departmentChart() {
        return chartDao.departmentChart();
    }

    @Override
    public List<ChartBean> positionChart() {
        return chartDao.positionChart();
    }
}
