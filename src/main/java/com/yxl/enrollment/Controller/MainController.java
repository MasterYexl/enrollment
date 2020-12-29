package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Tool.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    Check check;
    @GetMapping("/index")
    public String index(Model model, HttpSession session){
        Student student;
        SignState signState = (SignState) session.getAttribute("signState");
        if (signState != null) {
            student = signState.getStudent();
            model.addAttribute("student",student);
        }
        return "index";
    }

}
