package com.neusoft.hr.business.responsitory;

import com.neusoft.hr.business.entity.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionDao {
    List<Position> queryAllPosition();

    List<Position> findPositionByName(@Param("name") String queryKey);

    void delete(Long id);

    void insert(Position position);

    void update(Position position);
}
