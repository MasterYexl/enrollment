package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Mapper.StudentInformationMapper;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.*;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.DirectionImpl;
import com.yxl.enrollment.Tool.Factory;
import com.yxl.enrollment.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    DirectionImpl directionImpl;
    @GetMapping("/tutor")
    String getAllTutors(Model model){
        List<Tutor> tutors = tutorMapper.selectAll();
        for (Tutor tutor : tutors){
            System.out.println(tutor);
        }
        model.addAttribute("tutors", tutors);
        return "Module/tutor-list";
    }

    @GetMapping("/tutor/{id}")
    String getTutor(@PathVariable("id") Integer id, Model model){
        Tutor tutor = tutorMapper.selectById(id);
        System.out.println(tutor);
        TutorInformation tutorInformation = tutorInformationMapper.selectByTid(id);
        model.addAttribute("tutor",tutor);
        model.addAttribute("ti",tutorInformation);
        return "/Module/tutor-view-information";
    }
    
    @GetMapping("/enroll")
    String enroll(HttpSession session, Model model){
        Tool.setMsg(model, session);
        SignState signState = (SignState) session.getAttribute("signState");
        Student student = signState.getStudent();
        StudentInformation studentInformation = studentInformationMapper.selectBySid(student.getSid());
        List<Direction> directions = directionImpl.getAll();
        model.addAttribute("dirs", directions);
        model.addAttribute("student", student);
        model.addAttribute("sifo",studentInformation);
        return "/Module/entry";
    }

    @PostMapping("/do-enroll")
    String doEnroll(@ModelAttribute StudentInformation studentInformation,HttpSession session, Model model){
        String msg = "";
        try {
            SignState signState = (SignState) session.getAttribute("signState");
            Student student = signState.getStudent();
            StudentInformation old = studentInformationMapper.selectBySid(student.getSid());
            if (old.getSecondDirection()!=null) {
                msg = "你已经报名，不能重复提交";
                session.setAttribute("msg", msg);
                return "redirect:/user/enroll";
            }
            old.setFirstDirection(studentInformation.getFirstDirection());
            old.setSecondDirection(studentInformation.getSecondDirection());
            studentInformationMapper.updateBySid(old);
            msg = "报名成功！";
        }catch (Exception e){
            e.printStackTrace();
            msg = "报名失败，请稍后再试";
        }
        session.setAttribute("msg",msg);
        return "redirect:/user/enroll";
    }

}
