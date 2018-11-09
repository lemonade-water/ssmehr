package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.EmlpoyeeTranform;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmlpoyeeTranformDao {
    void insertEm(EmlpoyeeTranform emlpoyeeTranform);

    List<EmlpoyeeTranform> queryAll();
}
