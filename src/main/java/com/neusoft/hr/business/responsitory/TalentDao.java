package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Talent;
import com.neusoft.hr.business.unit.PageBean;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentDao {
    List<Talent> queryAllTalent(int n,int limit,@Param("query") String query);

    int getTotal(@Param("query")String query);

    void updateTalent(Talent talent);

    Talent queryTalentById(long id);
}
