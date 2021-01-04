package com.yxl.enrollment.Service;

import com.yxl.enrollment.Module.MySql.Direction;

public interface DirectionService {
    int addDirection(Direction direction);
    Direction getDirectionByTutor(String tutorName);
    Direction getDirectionByDid(Integer did);
    Direction getDirectionByDirectionName(String name);
    int updateDirectionBy(Direction direction);
    int deleteByDid(int did);

}
