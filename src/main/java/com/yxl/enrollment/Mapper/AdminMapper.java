package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    @Select("select * from student where sid = #{aid}")
    public Admin selectById(Integer aid);
    @Select("select password from student where sid = #{aid}")
    public String selectPasswordById(Integer aid);
}
