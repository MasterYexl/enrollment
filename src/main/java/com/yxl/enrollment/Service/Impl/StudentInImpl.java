package com.yxl.enrollment.Service.Impl;

import com.yxl.enrollment.Mapper.*;
import com.yxl.enrollment.Module.MySql.*;
import com.yxl.enrollment.Service.StudentService;
import com.yxl.enrollment.Service.TutorInformationService;
import com.yxl.enrollment.Tool.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentInImpl implements StudentService {
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MessageMapper messageMapper;

    @Override
    public int addStudentInformation(StudentInformation studentInformation) {
        return 0;
    }

    @Override
    public StudentInformation getStudentInformation(int sid) {
        return null;
    }

    @Override
    public int addStudent(Student student) {
        studentMapper.insert(student);
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.setSid(student.getSid());
        return studentInformationMapper.insert(studentInformation);
    }
}