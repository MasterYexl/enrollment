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

    public boolean checkStudent(Student student) {
        if (student.getName().length() <= 20 && student.getPassword().length() <= 20
                && student.getUniverse().length() <= 30 && student.getUniverse().length() <= 30
                && student.getSpecialities().length() <= 30 && student.getEmail().length() <= 32
                && student.getEmail().contains("@")) {
            return true;
        }
        return false;
    }

    public boolean checkAdmin(Admin admin) {
        if (admin.getName().length() <= 20 && admin.getPassword().length() <= 20) {
            return true;
        }
        return false;
    }

    public boolean checkTutor(Tutor tutor) {
        if (tutor.getName().length() <= 20 && tutor.getPassword().length() <= 20
                && tutor.getEmail().length() <= 32 && tutor.getEmail().contains("@")) {
            return true;
        }
        return false;
    }

    public boolean checkStudentInformation(StudentInformation studentInformation) {
        if (studentInformation.getGrade().length() <= 255
                && studentInformation.getFirstDirection() != null
                && studentInformation.getSecondDirection() != null
                && studentInformation.getProcess() != null) {
            return true;
        }
        return false;
    }

    public boolean checkTutorInformation(TutorInformation tutorInformation) {
        if (tutorInformation.getDirection().length() <= 32
                && tutorInformation.getEmail().length() <= 32
                && tutorInformation.getEmail().contains("@")
                && tutorInformation.getTutorRequire().length() <= 255
                && tutorInformation.getTel().length()==11) {
            return true;
        }
        return false;
    }


    public Student CheckStudentPassword(Student student) {
        int id = student.getSid();
        String email = student.getEmail();
        Student orgStudent = null;
        if (id != -1) orgStudent = studentMapper.selectById(id);
        else if (email.contains("@")) orgStudent = studentMapper.selectByEmail(email);
        if (orgStudent == null) return null;
        if (orgStudent.getPassword().equals(student.getPassword())) return orgStudent;
        else return null;
    }

    public Tutor CheckTutorPassword(Tutor tutor) {
        int id = tutor.getTid();
        String email = tutor.getEmail();
        Tutor orgTutor = null;
        if (id != -1) orgTutor = tutorMapper.selectById(id);
        else if (email.contains("@")) orgTutor = tutorMapper.selectByEmail(email);
        if (orgTutor == null) return null;
        if (orgTutor.getPassword().equals(tutor.getPassword())) return orgTutor;
        else return null;
    }

    public Admin CheckAdminPassword(Admin admin) {
        int id = admin.getAid();
        Admin orgAdmin = adminMapper.selectById(id);
        if (orgAdmin == null) return null;
        if (orgAdmin.getPassword().equals(admin.getPassword())) return orgAdmin;
        else return null;
    }
}
