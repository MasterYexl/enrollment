package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Mapper.StudentMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Conponent.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    StudentMapper userMapper;
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    Check check;

    @GetMapping("/student")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "signup";
    }
    @ResponseBody
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String doAddStudent(@ModelAttribute Student student){
        System.out.println(student);
        userMapper.insertSelective(student);
        return "注册成功";
    }
    @GetMapping("/tutor")
    public String addTutor(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "signup";
    }
    @ResponseBody
    @RequestMapping(value = "/tutor", method = RequestMethod.POST)
    public String doAddTutor(@ModelAttribute Tutor tutor){
        System.out.println(tutor);
        tutorMapper.insertSelective(tutor);
        return "注册成功";
    }
    @GetMapping("/login")
    public String getSignPage(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "login";
    }
    @PostMapping("/student-login")
    public String signIn(@ModelAttribute Student student, HttpSession session,Model model){
        Student orgStudent = check.CheckStudent(student);
        if (orgStudent != null){
            SignState signState = new SignState();
            Date date = new Date();
            signState.setRole(0);
            signState.setSignDate(date);
            signState.setUser(orgStudent);
            signState.setName(orgStudent.getName());
            session.setAttribute("signState",signState);
            model.addAttribute("signState",signState);
            return "redirect:/admin";
        }
        model.addAttribute("msg","登录失败");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("signState");
        return "redirect:/sign/login";
    }

    @GetMapping("/lock")
    public String lock(Model model,HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        signState.setLock(true);
        session.setAttribute("signState",signState);
        model.addAttribute("signState",signState);
        return "locked";
    }

    @PostMapping("/unlock")
    public String unlock(String password, HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        String orgPassword = "";
        if (signState != null) {
            if (signState.getRole()==0) orgPassword = signState.getStudent().getPassword();
            if (signState.getRole()==1) orgPassword = signState.getTutor().getPassword();
            if (signState.getRole()>1) orgPassword = signState.getAdmin().getPassword();
            if (orgPassword.equals(password)) signState.setLock(false);
        }
        session.setAttribute("signState",signState);
        return "redirect:/admin";
    }

}
