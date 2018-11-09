package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.Position;
import com.neusoft.hr.business.responsitory.PositionDao;
import com.neusoft.hr.business.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImp implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Override
    public List<Position> queryAllPosition() {
        return positionDao.queryAllPosition();
    }

    public List<Position> findPositionByName(String queryKey) {
        return positionDao.findPositionByName(queryKey);
    }

    public void delete(Long id) {
        positionDao.delete(id);
    }

    public void insert(Position position) {
        positionDao.insert(position);

    }

    public void update(Position position) {
        positionDao.update(position);
    }

}
