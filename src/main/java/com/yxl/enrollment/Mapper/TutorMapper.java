package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TutorMapper extends Mapper<Tutor> {
    @Select("select * from tutor where tid = #{tid}")
    public Tutor selectById(Integer tid);
    @Select("select password from student where sid = #{tid}")
    public String selectPasswordById(Integer tid);
}
