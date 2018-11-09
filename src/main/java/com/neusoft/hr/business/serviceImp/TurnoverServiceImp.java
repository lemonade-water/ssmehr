package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.EmlpoyeeTranform;
import com.neusoft.hr.business.responsitory.EmlpoyeeTranformDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoverServiceImp {
    @Autowired
    private EmlpoyeeTranformDao emlpoyeeTranformDao;

    List<EmlpoyeeTranform> queryAll(){
        return emlpoyeeTranformDao.queryAll();
    }
}
