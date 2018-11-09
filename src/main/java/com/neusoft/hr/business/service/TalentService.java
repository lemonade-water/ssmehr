package com.neusoft.hr.business.service;

import com.neusoft.hr.business.entity.Talent;
import com.neusoft.hr.business.unit.PageBean;

import java.util.List;

public interface TalentService {
    PageBean<Talent> queryAllTalentLimit(PageBean<Talent> pageBean,String qurey);
     void updateTalent(Talent talent);
}
