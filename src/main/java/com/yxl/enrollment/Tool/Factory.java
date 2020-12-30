package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Module.SignState;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
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
}
