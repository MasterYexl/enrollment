package com.yxl.enrollment.Module;

import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;

import java.util.Date;

public class SignState {
    private Object user;
    private Date signDate;
    private String name;
    private int role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;

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
        if (role==0) setRoleName("学生");
        if (role==1) setRoleName("导师");
        if (role>1) setRoleName("管理员");
        this.role = role;
    }
}
