package com.yxl.enrollment.Module;

import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;

import java.util.Date;

public class SignState {
    private Object user;
    private Date signDate;
    private int role;

    public Student getStudent() {
        return (Student) user;
    }

    public Tutor getTutor() {
        return (Tutor) user;
    }

    public Admin getAdmin() {
        return (Admin) user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
