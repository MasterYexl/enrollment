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

    public int checkStudent(Student student) {
        if (baseCheek(student.getName(), 20)
                && baseCheek(student.getPassword(), 20)
                && baseCheek(student.getUniverse(), 30)
                && baseCheek(student.getSpecialities(), 30)
                && baseCheek(student.getEmail(), 32)
                && student.getEmail().contains("@")) {
            Student getStudent = studentMapper.selectByEmail(student.getEmail());
            if (getStudent == null) {
                return 1;
            }
            else return -1;
        }
        return 0;
    }

    public boolean checkAdmin(Admin admin) {
        if (baseCheek(admin.getName(), 20) && baseCheek(admin.getPassword(), 20)) {
            return true;
        }
        return false;
    }

    public int checkTutor(Tutor tutor) {
        if (baseCheek(tutor.getName(), 20)
                && baseCheek(tutor.getPassword(), 20)
                && baseCheek(tutor.getEmail(), 32)
                && tutor.getEmail().contains("@")) {
            Tutor test = tutorMapper.selectByEmail(tutor.getEmail());
            if (test == null) return 1;
            else return -1;
        }
        return 0;
    }

    public boolean checkStudentInformation(StudentInformation studentInformation) {
        if (baseCheek(studentInformation.getGrade(), 255)
                && studentInformation.getFirstDirection() != null
                && studentInformation.getSecondDirection() != null
                && studentInformation.getProcess() != null) {
            return true;
        }
        return false;
    }

    public boolean checkTutorInformation(TutorInformation tutorInformation) {

        if (baseCheek(tutorInformation.getDirection(), 32)
                && baseCheek(tutorInformation.getTutorRequire(), 255)) {
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

    public static boolean baseCheek(String str, int length) {
        if (str != null && !"".equals(str) && str.length() <= length) {
            return true;
        }
        return false;
    }
}
