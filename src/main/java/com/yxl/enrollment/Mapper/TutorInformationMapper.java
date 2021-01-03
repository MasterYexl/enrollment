package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.TutorInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface TutorInformationMapper extends Mapper<TutorInformation> {
    @Override
    @Insert("insert into tutor_information(tid, direction, enrollment_number, tel, email, scholarship, tutor_require, display) values(#{tid}, #{direction}, #{enrollmentNumber},#{tel},#{email},#{scholarship},#{tutorRequire},#{display})")
    int insert(TutorInformation tutorInformation);

    @Select("select * from tutor_information where tid = #{tid}")
    TutorInformation selectByTid(int tid);

    @Override
    @Update("update tutor_information set direction = #{direction}, enrollment_number = #{enrollmentNumber}, tel = #{tel}, email = #{email}, scholarship = #{scholarship}, tutor_require = #{tutorRequire}, display = #{display}")
    int updateByPrimaryKey(TutorInformation tutorInformation);

    @Update("update tutor_information set display = #{display} where tid = #{tid}")
    int updateDisplayByTid(int tid, int display);
}
