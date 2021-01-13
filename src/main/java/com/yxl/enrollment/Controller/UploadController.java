package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Mapper.StudentInformationMapper;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Module.MySql.Student;
import com.yxl.enrollment.Module.MySql.StudentInformation;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.TutorInImpl;
import com.yxl.enrollment.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    Check check;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    TutorInImpl tutorInImpl;

    @GetMapping("/tutor-info")
    public String tutorInformationPage(Model model, HttpSession session) {
        Tool.setMsg(model, session);
        SignState signState = (SignState) session.getAttribute("signState");
        Tutor tutor = signState.getTutor();
        TutorInformation tutorInformation = tutorInImpl.getTutorInformation(tutor.getTid());
        if (tutorInformation.getEmail() == null || tutorInformation.getEmail().equals(""))
            tutorInformation.setEmail(tutor.getEmail());
        model.addAttribute("tifo", tutorInformation);
        return "Module/tutor-information";
    }

    @PostMapping("add-tutor-info")
    public String tutorInfo(@ModelAttribute TutorInformation tutorInformation, HttpSession session) {
        if (check.checkTutorInformation(tutorInformation)) {
            try {
                SignState signState = (SignState) session.getAttribute("signState");
                Tutor tutor = signState.getTutor();
                tutorInformation.setTid(tutor.getTid());
                int tmpInfo = tutorInImpl.addTutorInformation(tutorInformation);
                if (tmpInfo == -1) session.setAttribute("msg", "信息未变更");
                else session.setAttribute("msg", "提交成功，等待管理员审核");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("msg", "提交失败，请稍后再试");
            }
        } else session.setAttribute("msg", "提交失败，参数错误");
        return "redirect:/upload/tutor-info";
    }

    @GetMapping("/student-info")
    public String uploadStudent(Model model, HttpSession session) {
        SignState signState = (SignState) session.getAttribute("signState");
        Student student = signState.getStudent();
        StudentInformation studentInformation = studentInformationMapper.selectBySid(student.getSid());
        model.addAttribute("sifo", studentInformation);
        return "Module/student-information";
    }

    @ResponseBody
    @PostMapping("add-student-resume")
    public String studentInfo(@RequestParam("file") MultipartFile file, HttpSession session) {
        boolean b = uploadToStudentLocal(file, session, "resume");
        return b? "上传成功": "上传失败";
    }

    @ResponseBody
    @PostMapping("add-student-certificate")
    public String studentCertificate(@RequestParam("file") MultipartFile file, HttpSession session) {
        System.out.println("上传证书");
        SignState signState = (SignState) session.getAttribute("signState");
        Student student = signState.getStudent();
        StudentInformation studentInformation = studentInformationMapper.selectBySid(student.getSid());
        if (studentInformation == null) studentInformation = new StudentInformation();
        System.out.println(studentInformation);
        boolean b = uploadToStudentLocal(file, session, studentInformation.getSid()+"-"+studentInformation.getCertificate());
        if (b){
            studentInformation.setCertificate(studentInformation.getCertificate()+1);
            System.out.println(studentInformation);
            studentInformationMapper.updateBySid(studentInformation);
            return "上传成功";
        }
        return "上传失败";
    }

    public boolean uploadToStudentLocal(MultipartFile file, HttpSession session, String name) {
        try {
            SignState signState = (SignState) session.getAttribute("signState");
            Student student = signState.getStudent();
            if (file.isEmpty()) return false;
            String fn = file.getOriginalFilename();
            if (fn == null) return false;
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "static"+"/student/" + student.getId() + "/";
            System.out.println(filePath);
            File dest = new File(filePath);
            if (!dest.exists()){
                System.out.println("创建了文件"+dest.getAbsolutePath());
                dest.mkdirs();
            }
            String fileSuffix = fn.substring(fn.lastIndexOf("."));
            dest = new File(filePath + name + fileSuffix);
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
