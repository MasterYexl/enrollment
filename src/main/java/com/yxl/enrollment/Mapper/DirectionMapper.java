package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Direction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface DirectionMapper extends Mapper<Direction> {
    @Select("select * from direction where direction_name=#{name}")
    Direction selectByDirectionName(String name);

    @Delete("delete from direction where did=#{did}")
    int deleteByDid(Integer did);

    @Insert("insert into direction(direction_name, teachers) values(#{directionName}, #{teachers})")
    @Override
    int insert(Direction direction);
    @Update("update direction set direction_name=#{directionName}, teachers=#{teachers} where did=#{did}")
    int updateByDid(Direction direction);
}
