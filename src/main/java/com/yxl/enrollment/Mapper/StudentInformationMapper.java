package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.StudentInformation;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface StudentInformationMapper extends Mapper<StudentInformation> {
    @Select("select * from student_information where sid = #{sid}")
    StudentInformation selectBySid(int sid);
    @Update("update student_information set resume = #{resume}, certificate = #{certificate}, grade = #{grade}, second_direction = #{secondDirection}, first_direction = #{firstDirection}, process = #{process} where sid = #{sid}")
    int updateBySid(StudentInformation studentInformation);

    @Insert("insert into student_information(sid, resume, certificate, grade, second_direction, first_direction, process) values(#{sid}, #{resume}, #{certificate}, #{grade}, #{secondDirection}, #{firstDirection}, #{process})")
    @Override
    int insert(StudentInformation studentInformation);
}
