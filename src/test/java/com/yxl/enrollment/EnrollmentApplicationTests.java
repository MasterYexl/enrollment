package com.yxl.enrollment;

import com.yxl.enrollment.Mapper.ComboStudentMapper;
import com.yxl.enrollment.Module.MySql.ComboStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EnrollmentApplicationTests {

    @Autowired
    ComboStudentMapper comboStudentMapper;

    @Test
    void contextLoads() {
        List<ComboStudent> comboStudents = comboStudentMapper.selectByFirstDirection(3);
        System.out.println(comboStudents);
    }


}
