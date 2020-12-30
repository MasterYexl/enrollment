package com.yxl.enrollment.Tool;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public class Tool {
    public static void setMsg(Model model, HttpSession session){
        model.addAttribute("msg",session.getAttribute("msg"));
        session.removeAttribute("msg");
    }
}
