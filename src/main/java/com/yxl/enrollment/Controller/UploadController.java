package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.TutorInformationImpl;
import com.yxl.enrollment.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    Check check;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    TutorInformationImpl tutorInformationImpl;
    @GetMapping("/tutor-info")
    public String tutorInformationPage(Model model, HttpSession session){
        Tool.setMsg(model,session);
        SignState signState = (SignState) session.getAttribute("signState");
        Tutor tutor = signState.getTutor();
        TutorInformation tutorInformation = tutorInformationImpl.getTutorInformation(tutor.getTid());
        if (tutorInformation.getEmail()==null||tutorInformation.getEmail().equals("")) tutorInformation.setEmail(tutor.getEmail());
        System.out.println(tutorInformation);
        model.addAttribute("tifo", tutorInformation);
        return "Module/tutor-information";
    }
    @PostMapping("add-tutor-info")
    public String tutorInfo(@ModelAttribute TutorInformation tutorInformation,HttpSession session){
        if (check.checkTutorInformation(tutorInformation)){
            try {
                SignState signState = (SignState) session.getAttribute("signState");
                Tutor tutor = signState.getTutor();
                tutorInformation.setTid(tutor.getTid());
                int tmpInfo = tutorInformationImpl.addTutorInformation(tutorInformation);
                if (tmpInfo == -1) session.setAttribute("msg","信息未变更");
                else session.setAttribute("msg","提交成功，等待管理员审核");
            }
            catch (Exception e){
                e.printStackTrace();
                session.setAttribute("msg","提交失败，请稍后再试");
            }
        }
        else session.setAttribute("msg", "提交失败，参数错误");
        return "redirect:/upload/tutor-info";
    }
}
