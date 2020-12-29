package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper extends tk.mybatis.mapper.common.Mapper<Student> {
    @Select("select * from student where sid = #{sid}")
    public Student selectBySid(Integer sid);
    @Select("select password from student where sid = #{sid}")
    public String selectPasswordBySid(Integer sid);
}
