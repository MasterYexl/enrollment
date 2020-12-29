package com.yxl.enrollment.Module.MySql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Enrollment {
    private int sid;
    private int tid;
    private int did;
    private String eid;

    @Basic
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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
    @Column(name = "did", nullable = false)
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return sid == that.sid &&
                tid == that.tid &&
                did == that.did;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, tid, did);
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    @Id
    public String getEid() {
        return eid;
    }
}
