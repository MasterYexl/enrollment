package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Direction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DirectionMapper extends Mapper<Direction> {
    @Select("select * from direction where direction_name=#{name}")
    Direction selectByDirectionName(String name);

    @Select("select * from direction where teachers=#{tid}")
    Direction selectByTid(int tid);

    @Select("select * from direction where did=#{id}")
    Direction selectById(int id);

    @Select("select * from direction")
    @Override
    List<Direction> selectAll();

    @Delete("delete from direction where did=#{did}")
    int deleteByDid(Integer did);

    @Insert("insert into direction(direction_name, teachers) values(#{directionName}, #{teachers})")
    @Override
    int insert(Direction direction);
    @Update("update direction set direction_name=#{directionName}, teachers=#{teachers} where did=#{did}")
    int updateByDid(Direction direction);


}
