package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Module.MySql.Student;

/**
 * @author unborn
 * @date 2021-01-04 10:02
 */
public class TestMain {
    public static void main(String[] args){
        Student student = new Student();
        student.setId(1);
        student.setName("张珊珊");
        student.setPassword("626532");
        student.setUniverse("哈哈哈");
        student.setSpecialities("hahah");
        student.setEmail("626532990qq.com");

        Check check = new Check();
        boolean boolStu = check.checkStudent(student);
        System.out.println("boolStu:" + boolStu);
    }
}
