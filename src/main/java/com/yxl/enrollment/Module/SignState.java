package com.yxl.enrollment.Module;

import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.User;

import java.util.Date;

public class SignState {
    private User user;
    private Date signDate;
    private boolean lock;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getRoleName() {
        if (user.getRole()==0) return "学生";
        if (user.getRole()==1||user.getRole()==2) return "导师";
        if (user.getRole()>2) return "管理员";
        return "错误";
    }

    public Student getStudent() {
        return (Student) user;
    }

    public Tutor getTutor() {
        return (Tutor) user;
    }

    public Admin getAdmin() {
        return (Admin) user;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

}
