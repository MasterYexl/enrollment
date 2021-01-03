package com.yxl.enrollment.Module.MySql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task {
    private int taskId;
    private int owner;
    private Integer taskClass;
    private String name;
    private String describe;
    private byte complete;

    @Id
    @Column(name = "task_id", nullable = false)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "owner", nullable = false)
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "task_class", nullable = true)
    public Integer getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(Integer taskClass) {
        this.taskClass = taskClass;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 16)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "describe", nullable = false, length = 255)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "complete", nullable = false)
    public byte getComplete() {
        return complete;
    }

    public void setComplete(byte complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                owner == task.owner &&
                complete == task.complete &&
                Objects.equals(taskClass, task.taskClass) &&
                Objects.equals(name, task.name) &&
                Objects.equals(describe, task.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, owner, taskClass, name, describe, complete);
    }
}
