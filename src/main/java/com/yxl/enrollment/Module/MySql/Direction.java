package com.yxl.enrollment.Module.MySql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Direction {
    private int did;
    private String directionName;
    private String teachers;

    @Id
    @Column(name = "did", nullable = false)
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Basic
    @Column(name = "direction_name", nullable = false, length = 30)
    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    @Basic
    @Column(name = "teachers", nullable = false, length = 255)
    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return did == direction.did &&
                Objects.equals(directionName, direction.directionName) &&
                Objects.equals(teachers, direction.teachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, directionName, teachers);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "did=" + did +
                ", directionName='" + directionName + '\'' +
                ", teachers='" + teachers + '\'' +
                '}';
    }
}
