package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TutorMapper extends Mapper<Tutor> {
    @Select("select * from tutor where tid = #{tid}")
    Tutor selectById(Integer tid);
    @Select("select * from tutor where email = #{email}")
    Tutor selectByEmail(String email);
    @Select("select password from tutor where tid = #{tid}")
    String selectPasswordById(Integer tid);
}
