package com.yxl.enrollment.Module.MySql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "student_information", schema = "enrollment", catalog = "")
public class StudentInformation {
    private int sid;
    private Byte resume=0;
    private Integer certificate=0;
    private String grade;
    private Integer secondDirection;
    private Integer firstDirection;
    private Integer process;

    @Basic
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "resume", nullable = true)
    public Byte getResume() {
        return resume;
    }

    public void setResume(Byte resume) {
        this.resume = resume;
    }

    @Basic
    @Column(name = "certificate", nullable = true)
    public Integer getCertificate() {
        return certificate;
    }

    public void setCertificate(Integer certificate) {
        this.certificate = certificate;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 255)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "second_direction", nullable = true)
    public Integer getSecondDirection() {
        return secondDirection;
    }

    public void setSecondDirection(Integer secondDirection) {
        this.secondDirection = secondDirection;
    }

    @Basic
    @Column(name = "first_direction", nullable = true)
    public Integer getFirstDirection() {
        return firstDirection;
    }

    public void setFirstDirection(Integer firstDirection) {
        this.firstDirection = firstDirection;
    }

    @Basic
    @Column(name = "process", nullable = true)
    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInformation that = (StudentInformation) o;
        return sid == that.sid &&
                Objects.equals(resume, that.resume) &&
                Objects.equals(certificate, that.certificate) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(secondDirection, that.secondDirection) &&
                Objects.equals(firstDirection, that.firstDirection) &&
                Objects.equals(process, that.process);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, resume, certificate, grade, secondDirection, firstDirection, process);
    }

    @Override
    public String toString() {
        return "StudentInformation{" +
                "sid=" + sid +
                ", resume=" + resume +
                ", certificate=" + certificate +
                ", grade='" + grade + '\'' +
                ", secondDirection=" + secondDirection +
                ", firstDirection=" + firstDirection +
                ", process=" + process +
                '}';
    }
}
