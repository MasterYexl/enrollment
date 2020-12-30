package com.yxl.enrollment.Module.MySql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private int mid;
    private String fromEmail;
    private String toEmail;
    private String message;
    private Timestamp messageTime;

    @Id
    @Column(name = "mid", nullable = false)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "from_email", nullable = false, length = 32)
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Basic
    @Column(name = "to_email", nullable = false, length = 32)
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "message_time", nullable = false)
    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return mid == message1.mid &&
                Objects.equals(fromEmail, message1.fromEmail) &&
                Objects.equals(toEmail, message1.toEmail) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(messageTime, message1.messageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, fromEmail, toEmail, message, messageTime);
    }
}
