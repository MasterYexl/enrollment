package com.yxl.enrollment.Module.MySql;

public interface User {
    int getId();
    void setId(int id);
    int getRole();
    void setRole(int role);
    String getEmail();
    void setEmail(String email);
    String getName();
    void setName(String name);
}
