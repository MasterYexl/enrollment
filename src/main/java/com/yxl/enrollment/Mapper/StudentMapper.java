package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper extends tk.mybatis.mapper.common.Mapper<Student> {
    @Select("select * from student where sid = #{sid}")
    Student selectById(Integer sid);
    @Select("select * from student where email = #{email}")
    Student selectByEmail(String email);
    @Select("select password from student where sid = #{sid}")
    String selectPasswordById(Integer sid);

    @Override
    @Select("select * from student")
    List<Student> selectAll();
}
