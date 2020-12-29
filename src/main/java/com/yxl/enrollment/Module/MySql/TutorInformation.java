package com.yxl.enrollment.Module.MySql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tutor_information", schema = "enrollment", catalog = "")
public class TutorInformation {
    private int tid;
    private String direction;
    private String tel;
    private String email;
    private Integer scholarship;
    private String request;
    private byte display;

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "direction", nullable = false, length = 30)
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "tel", nullable = false, length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "scholarship", nullable = true)
    public Integer getScholarship() {
        return scholarship;
    }

    public void setScholarship(Integer scholarship) {
        this.scholarship = scholarship;
    }

    @Basic
    @Column(name = "request", nullable = false, length = 255)
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Basic
    @Column(name = "display", nullable = false)
    public byte getDisplay() {
        return display;
    }

    public void setDisplay(byte display) {
        this.display = display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TutorInformation that = (TutorInformation) o;
        return tid == that.tid &&
                display == that.display &&
                Objects.equals(direction, that.direction) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(email, that.email) &&
                Objects.equals(scholarship, that.scholarship) &&
                Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, direction, tel, email, scholarship, request, display);
    }
}
