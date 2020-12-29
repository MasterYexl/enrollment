package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Mapper.StudentMapper;
import com.yxl.enrollment.Module.MySql.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Check {
    @Autowired
    private StudentMapper studentMapper;
    public Student CheckStudent(Student student){
        int id = student.getSid();
        Student orgStudent = studentMapper.selectBySid(id);
        if (orgStudent==null) return null;
        if (orgStudent.getPassword().equals(student.getPassword())) return orgStudent;
        else return null;
    }
}
