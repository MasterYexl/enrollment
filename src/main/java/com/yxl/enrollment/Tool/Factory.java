package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Module.MySql.Message;
import com.yxl.enrollment.Module.SignState;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Factory {
    public static SignState createSignState(Object user, String userName, int role, HttpSession session, Model model){
        SignState signState = new SignState();
        Date date = new Date();
        signState.setRole(role);
        signState.setSignDate(date);
        signState.setUser(user);
        signState.setName(userName);
        session.setAttribute("signState",signState);
        session.removeAttribute("vc");
        model.addAttribute("signState",signState);
        return signState;
    }
    public static Message createMessage(String from,String to, String info){
        Message message = new Message();
        message.setFromEmail(from);
        message.setMessage(info);
        message.setToEmail(to);
        LocalDateTime dateTime = LocalDateTime.now();
        message.setMessageTime(Timestamp.valueOf(dateTime));
        return message;
    }

}
