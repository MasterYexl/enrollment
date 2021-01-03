package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.TutorInImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    Check check;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    TutorInImpl tutorIn;

    @ResponseBody
    @GetMapping("/tutor-pass/{tid}")
    public String tutorPass(@PathVariable("tid") Integer tid){
        Tutor tutor = tutorMapper.selectById(tid);
        int info = tutorIn.passTutorInformation(tutor, true, "您的录取要求审核通过");
        if (info == 1) return "1";
        return "0";
    }

    @ResponseBody
    @GetMapping("/tutor-not-pass/{tid}")
    public String tutorNotPass(@PathVariable("tid") Integer tid,@RequestParam("message") String message){
        Tutor tutor = tutorMapper.selectById(tid);
        int info = tutorIn.passTutorInformation(tutor, false, message);
        if (info == 1) return "1";
        return "0";
    }

}
