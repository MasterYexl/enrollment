package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Conponent.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
        SignState signState = (SignState) session.getAttribute("signState");
        if (signState != null) {
            if (signState.getUser().getRole()==0) {
                Student student = signState.getStudent();
                model.addAttribute("student",student);
            }
            if (signState.getUser().getRole()==1){
                Tutor tutor = signState.getTutor();
                model.addAttribute("tutor",tutor);
            }
            if (signState.getUser().getRole()>1){
                Admin admin = signState.getAdmin();
                model.addAttribute("tutor",admin);
            }
        }
        model.addAttribute("signState",signState);
        return "index";
    }
    @GetMapping("/admin")
    public String admin(Model model, HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        model.addAttribute("signState",signState);
        return "admin-index";
    }
}
