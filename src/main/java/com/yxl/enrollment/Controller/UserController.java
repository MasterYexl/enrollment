package com.yxl.enrollment.Controller;

import com.alibaba.fastjson.JSON;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
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
}
