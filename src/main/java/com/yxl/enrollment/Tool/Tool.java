package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Module.MySql.User;
import com.yxl.enrollment.Module.SignState;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public class Tool {
    public static void setMsg(Model model, HttpSession session){
        model.addAttribute("msg",session.getAttribute("msg"));
        session.removeAttribute("msg");
    }
    public static String getEmail(HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        User user = signState.getUser();
        return user.getEmail();
    }
}
