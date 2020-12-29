package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Mapper.StudentMapper;
import com.yxl.enrollment.Module.MySql.Student;
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
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String doAddTutor(@ModelAttribute Student student){
        System.out.println(student);
        userMapper.insertSelective(student);
        return "注册成功";
    }
    @GetMapping("/logging")
    public String getSignPage(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "sign";
    }
    @ResponseBody
    @PostMapping("/student-logging")
    public String signIn(@ModelAttribute Student student, HttpSession session){
        Student orgStudent = check.CheckStudent(student);
        if (orgStudent != null){
            SignState signState = new SignState();
            Date date = new Date();
            signState.setRole(0);
            signState.setSignDate(date);
            signState.setUser(orgStudent);
            session.setAttribute("signState",signState);
            return "登录成功";
        }
        return "登录失败";
    }



}
