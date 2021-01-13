package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.ComboStudent;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ComboStudentMapper extends Mapper<ComboStudent> {
    @Select("select * from student_information join student on student_information.sid = student.sid where first_direction=#{firstDirection} and process=0")
    List<ComboStudent> selectByFirstDirection(int firstDirection);
    @Select("select * from student_information join student on student_information.sid = student.sid where second_direction=#{secondDirection} and process=1")
    List<ComboStudent> selectBySecondDirection(int secondDirection);
}
