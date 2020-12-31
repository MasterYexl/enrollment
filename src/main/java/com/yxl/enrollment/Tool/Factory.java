package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Module.MySql.Message;
import com.yxl.enrollment.Module.MySql.User;
import com.yxl.enrollment.Module.SignState;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Factory {
    public static SignState createSignState(User user, HttpSession session, Model model){
        SignState signState = new SignState();
        Date date = new Date();
        signState.setSignDate(date);
        signState.setUser(user);
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
