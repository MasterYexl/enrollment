package com.yxl.enrollment.Conponent;

import com.yxl.enrollment.Mapper.AdminMapper;
import com.yxl.enrollment.Mapper.StudentMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检查合法性
 */

@Component
public class Check {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private AdminMapper adminMapper;

    public boolean checkStudent(Student student){
        return true;
    }
    public boolean checkAdmin(Admin admin){
        return true;
    }
    public boolean checkTutor(Tutor tutor){
        return true;
    }
    public boolean checkStudentInformation(StudentInformation studentInformation){
        return true;
    }
    public boolean checkTutorInformation(TutorInformation tutorInformation){
        return true;
    }

    public Student CheckStudentPassword(Student student){
        int id = student.getSid();
        String email = student.getEmail();
        Student orgStudent = null;
        if (id!=-1) orgStudent = studentMapper.selectById(id);
        else if (email.contains("@")) orgStudent = studentMapper.selectByEmail(email);
        if (orgStudent==null) return null;
        if (orgStudent.getPassword().equals(student.getPassword())) return orgStudent;
        else return null;
    }
    public Tutor CheckTutorPassword(Tutor tutor){
        int id = tutor.getTid();
        String email = tutor.getEmail();
        Tutor orgTutor = null;
        if (id != -1) orgTutor = tutorMapper.selectById(id);
        else if (email.contains("@")) orgTutor = tutorMapper.selectByEmail(email);
        if (orgTutor==null) return null;
        if (orgTutor.getPassword().equals(tutor.getPassword())) return orgTutor;
        else return null;
    }
    public Admin CheckAdminPassword(Admin admin){
        int id = admin.getAid();
        Admin orgAdmin = adminMapper.selectById(id);
        if (orgAdmin==null) return null;
        if (orgAdmin.getPassword().equals(admin.getPassword())) return orgAdmin;
        else return null;
    }
}
