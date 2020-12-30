package com.yxl.enrollment.Module.MySql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tutor_information", schema = "enrollment", catalog = "")
public class TutorInformation {
    private int tutorInfoId;
    private int tid;
    private String direction;
    private int enrollmentNumber;
    private String tel;
    private String email;
    private int scholarship;
    private String tutorRequire;
    private Integer display=0;

    @Id
    @Column(name = "tutor_info_id", nullable = false)
    public int getTutorInfoId() {
        return tutorInfoId;
    }

    public void setTutorInfoId(int tutorInfoId) {
        this.tutorInfoId = tutorInfoId;
    }

    @Basic
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "direction", nullable = false, length = 32)
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "enrollment_number", nullable = false)
    public int getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(int enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 32)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "scholarship", nullable = false)
    public int getScholarship() {
        return scholarship;
    }

    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }

    @Basic
    @Column(name = "tutor_require", nullable = false, length = 255)
    public String getTutorRequire() {
        return tutorRequire;
    }

    public void setTutorRequire(String tutorRequire) {
        this.tutorRequire = tutorRequire;
    }

    @Basic
    @Column(name = "display", nullable = true)
    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TutorInformation that = (TutorInformation) o;
        return tid == that.tid &&
                enrollmentNumber == that.enrollmentNumber &&
                scholarship == that.scholarship &&
                Objects.equals(direction, that.direction) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(email, that.email) &&
                Objects.equals(tutorRequire, that.tutorRequire) &&
                Objects.equals(display, that.display);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorInfoId, tid, direction, enrollmentNumber, tel, email, scholarship, tutorRequire, display);
    }

    @Override
    public String toString() {
        return "TutorInformation{" +
                "tutorInfoId=" + tutorInfoId +
                ", tid=" + tid +
                ", direction='" + direction + '\'' +
                ", enrollmentNumber=" + enrollmentNumber +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", scholarship=" + scholarship +
                ", tutorRequire='" + tutorRequire + '\'' +
                ", display=" + display +
                '}';
    }
}
