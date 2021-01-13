package com.yxl.enrollment.Service;

import com.yxl.enrollment.Module.MySql.Direction;

import java.util.List;

public interface DirectionService {
    int addDirection(Direction direction);
    Direction getDirectionByTutor(String tutorName);
    Direction getDirectionByDid(Integer did);
    Direction getDirectionByTid(Integer tid);
    Direction getDirectionByDirectionName(String name);
    int updateDirectionBy(Direction direction);
    int deleteByDid(int did);
    List<Direction> getAll();
}
