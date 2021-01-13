package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Mapper.*;
import com.yxl.enrollment.Module.ComboStudentList;
import com.yxl.enrollment.Module.MySql.*;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.DirectionImpl;
import com.yxl.enrollment.Service.Impl.StudentInImpl;
import com.yxl.enrollment.Tool.Factory;
import com.yxl.enrollment.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.LinkedList;
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
    MessageMapper messageMapper;
    @Autowired
    DirectionImpl directionImpl;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ComboStudentMapper comboStudentMapper;
    @Autowired
    StudentInImpl studentIn;
    @GetMapping("/tutor")
    String getAllTutors(Model model){
        List<Tutor> tutors = tutorMapper.selectAll();
        model.addAttribute("tutors", tutors);
        return "Module/tutor-list";
    }

    @GetMapping("/tutor/{id}")
    String getTutor(@PathVariable("id") Integer id, Model model){
        Tutor tutor = tutorMapper.selectById(id);
        TutorInformation tutorInformation = tutorInformationMapper.selectByTid(id);
        model.addAttribute("tutor",tutor);
        model.addAttribute("ti",tutorInformation);
        return "/Module/tutor-view-information";
    }

    @GetMapping("/student")
    String getAllStudent(Model model){
        List<Student> students = studentMapper.selectAll();
        model.addAttribute("students", students);
        model.addAttribute("path", 0);
        System.out.println(students);
        return "/Module/student-list";
    }

    @GetMapping("/student/{id}")
    String getStudent(@PathVariable("id") Integer id,Model model){
        System.out.println(id);
        Student student = studentMapper.selectById(id);
        StudentInformation studentInformation = studentInformationMapper.selectBySid(id);
        System.out.println(student);
        model.addAttribute("student", student);
        model.addAttribute("si", studentInformation);
        return "/Module/student-view-information";
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
            Message message = Factory.createMessage("-1", student.getEmail(), "你已成功报名我院研究生考试");
            messageMapper.insert(message);
            msg = "报名成功！";
        }catch (Exception e){
            e.printStackTrace();
            msg = "报名失败，请稍后再试";
        }
        session.setAttribute("msg",msg);
        return "redirect:/user/enroll";
    }

    @GetMapping("/admission-view")
    String getAdmission(Model model, HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        Tutor tutor = signState.getTutor();
        Direction direction = directionImpl.getDirectionByTid(tutor.getId());
        List<ComboStudent> comboStudents = comboStudentMapper.selectByFirstDirection(direction.getDid());
        if (comboStudents==null) comboStudents = new LinkedList<>();
        comboStudents.addAll(comboStudentMapper.selectBySecondDirection(direction.getDid()));
        ComboStudentList comboStudentList = new ComboStudentList();
        comboStudentList.setStudentLists(comboStudents);
        model.addAttribute("students",comboStudents);
        model.addAttribute("path", 1);
        session.setAttribute("students",comboStudentList);
        return "/Module/student-list";
    }

    @GetMapping("/admission-view/{id}")
    String getComboStudent(Model model, HttpSession session, @PathVariable("id") Integer id){
        ComboStudent student = getStudentFromSession(id,session);
        model.addAttribute("student", student);
        return "/Module/admission";
    }

    @ResponseBody
    @GetMapping("/admission/{id}")
    String doAdmission(Model model, HttpSession session, @PathVariable("id") Integer id, @RequestParam int admission){
        SignState signState = (SignState) session.getAttribute("signState");
        Tutor tutor = signState.getTutor();
        ComboStudent comboStudent = getStudentFromSession(id, session);
        StudentInformation student = studentIn.getStudentInformation(comboStudent.getSid());
        student.setGrade(comboStudent.getEmail());
        student.setProcess(comboStudent.getProcess());
        int admission1 = studentIn.admission(admission == 1, student);
        return admission1+"";
    }

    ComboStudent getStudentFromSession(int id,HttpSession session){
        ComboStudentList comboStudent = (ComboStudentList) session.getAttribute("students");
        List<ComboStudent> studentLists = comboStudent.getStudentLists();
        for (ComboStudent student : studentLists){
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

}
