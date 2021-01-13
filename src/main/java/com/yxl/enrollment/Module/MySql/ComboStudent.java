package com.yxl.enrollment.Module.MySql;

public class ComboStudent implements User{
    private int sid;
    private String name;
    private String password;
    private String universe;
    private String specialities;
    private String email;
    private Byte resume=0;
    private Integer certificate=0;
    private String grade;
    private Integer secondDirection;
    private Integer firstDirection;
    private Integer process;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    @Override
    public int getId() {
        return sid;
    }

    @Override
    public void setId(int id) {
        sid = id;
    }

    @Override
    public int getRole() {
        return 0;
    }

    @Override
    public void setRole(int role) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getResume() {
        return resume;
    }

    public void setResume(Byte resume) {
        this.resume = resume;
    }

    public Integer getCertificate() {
        return certificate;
    }

    public void setCertificate(Integer certificate) {
        this.certificate = certificate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getSecondDirection() {
        return secondDirection;
    }

    public void setSecondDirection(Integer secondDirection) {
        this.secondDirection = secondDirection;
    }

    public Integer getFirstDirection() {
        return firstDirection;
    }

    public void setFirstDirection(Integer firstDirection) {
        this.firstDirection = firstDirection;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "ComboStudent{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", universe='" + universe + '\'' +
                ", specialities='" + specialities + '\'' +
                ", email='" + email + '\'' +
                ", resume=" + resume +
                ", certificate=" + certificate +
                ", grade='" + grade + '\'' +
                ", secondDirection=" + secondDirection +
                ", firstDirection=" + firstDirection +
                ", process=" + process +
                '}';
    }
}
