package com.yxl.enrollment.Service;

import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.StudentInformation;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;

public interface StudentService {
    int addStudentInformation(StudentInformation studentInformation);

    StudentInformation getStudentInformation(int sid);

    int addStudent(Student student);


//    int passTutorInformation(Tutor tutor, boolean pass, String message);
}
