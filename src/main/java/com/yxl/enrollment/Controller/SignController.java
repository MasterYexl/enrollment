package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Mapper.StudentMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Tool.Factory;
import javafx.scene.control.ToolBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    public String addStudent(Model model, HttpSession session){
        setMsg(model,session);
        Student student = (Student) session.getAttribute("student");
        if (student==null) student = new Student();
        model.addAttribute(student);
        return "registration-student";
    }
    @PostMapping("add-student")
    public String doAddStudent(@ModelAttribute Student student, HttpSession session){
        try {
            userMapper.insertSelective(student);
            session.setAttribute("msg", "注册成功");
            return "redirect:/sign/login";
        }catch (Exception e){
            session.setAttribute("tutor", student);
            session.setAttribute("msg","服务器错误");
        }
        return "redirect:/sign/student";
    }
    @GetMapping("/tutor")
    public String addTutor(Model model, HttpSession session){
        setMsg(model,session);
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        if (tutor==null) tutor = new Tutor();
        model.addAttribute(tutor);
        return "registration";
    }
    @ResponseBody
    @PostMapping("add-tutor")
    public String doAddTutor(@ModelAttribute Tutor tutor, HttpSession session){
        try {
            tutorMapper.insertSelective(tutor);
            session.setAttribute("msg", "注册成功");
            return "redirect:/sign/login";
        }catch (Exception e){
            session.setAttribute("tutor", tutor);
            session.setAttribute("msg","服务器错误");
            return "redirect:/sign/tutor";
        }

    }
    @GetMapping("/login")
    public String getSignPage(Model model,HttpSession session){
        Student student = new Student();
        model.addAttribute(student);
        setMsg(model,session);
        return "login";
    }
    @PostMapping("login")
    public String login(String id,String password, String role,Model model,HttpSession session){
        try {
            String email = "";
            if (id.contains("@")){
                email = id;
                id = "-1";
            }
            if (role.equals("0")) {
                Student student = new Student();
                student.setSid(Integer.parseInt(id));
                student.setPassword(password);
                student.setEmail(email);
                return studentSignIn(student,session,model);
            }
            if (role.equals("1")){
                Tutor tutor = new Tutor();
                tutor.setTid(Integer.parseInt(id));
                tutor.setPassword(password);
                tutor.setEmail(email);
                return tutorSignIn(tutor, session, model);
            }
        }catch (Exception e){
            try {
                if (id.startsWith("::")) {
                    id = id.substring(2);
                    Admin admin = new Admin();
                    admin.setAid(Integer.parseInt(id));
                    admin.setPassword(password);
                    return adminSignIn(admin, session, model);
                }
            }
            catch (Exception ignored){}
        }
        return loginFail(model,session);
    }
    public String studentSignIn(Student student, HttpSession session,Model model){
        Student orgStudent = check.CheckStudent(student);
        if (orgStudent != null){
            Factory.createSignState(orgStudent, orgStudent.getName(), 0, session, model);
            return "redirect:/admin";
        }
        return loginFail(model,session);
    }
    public String tutorSignIn(Tutor tutor, HttpSession session,Model model){
        Tutor orgTutor = check.CheckTutor(tutor);
        if (orgTutor != null){
            Factory.createSignState(orgTutor, orgTutor.getName(), 1, session, model);
            return "redirect:/admin";
        }
        return loginFail(model,session);
    }
    public String adminSignIn(Admin admin, HttpSession session,Model model){
        Admin orgAdmin = check.CheckAdmin(admin);
        if (orgAdmin != null){
            Factory.createSignState(orgAdmin, orgAdmin.getName(), orgAdmin.getRole(), session, model);
            return "redirect:/admin";
        }
        return loginFail(model,session);
    }

    public String loginFail(Model model, HttpSession session){
        session.setAttribute("vc", true);
        model.addAttribute("msg","登录失败");
        return "login";
    }

    public void setMsg(Model model, HttpSession session){
        model.addAttribute("msg",session.getAttribute("msg"));
        session.removeAttribute("msg");
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
