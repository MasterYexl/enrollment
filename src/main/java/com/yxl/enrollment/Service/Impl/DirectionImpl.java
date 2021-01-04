package com.yxl.enrollment.Service.Impl;

import com.yxl.enrollment.Mapper.DirectionMapper;
import com.yxl.enrollment.Module.MySql.Direction;
import com.yxl.enrollment.Service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionImpl implements DirectionService {
    @Autowired
    DirectionMapper directionMapper;
    @Override
    public int addDirection(Direction direction) {
        Direction tryDirection = directionMapper.selectByDirectionName(direction.getDirectionName());
        if (tryDirection==null) return directionMapper.insert(direction);
        String teachers = tryDirection.getTeachers();
        teachers += direction.getTeachers();
        tryDirection.setTeachers(teachers);
        return directionMapper.insert(tryDirection);
    }

    @Override
    public Direction getDirectionByTutor(String tutorName) {
        return null;
    }

    @Override
    public Direction getDirectionByDid(Integer did) {
        return null;
    }

    @Override
    public Direction getDirectionByDirectionName(String name) {
        return directionMapper.selectByDirectionName(name);
    }

    @Override
    public int updateDirectionBy(Direction direction) {
        return directionMapper.updateByDid(direction);
    }

    @Override
    public int deleteByDid(int did) {
        return directionMapper.deleteByDid(did);
    }

    @Override
    public List<Direction> getAll() {
        return directionMapper.selectAll();
    }

}
