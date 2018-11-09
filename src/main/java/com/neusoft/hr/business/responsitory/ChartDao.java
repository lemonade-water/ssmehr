package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Department;
import com.neusoft.hr.business.unit.ChartBean;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ChartDao {
    List<ChartBean> departmentChart();

    List<ChartBean> positionChart();
}
