package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Module.SignState;
import com.yxl.enrollment.Service.Impl.StudentInImpl;
import com.yxl.enrollment.Service.Impl.TutorInImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(description = "管理员API接口")
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    Check check;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    StudentInImpl studentIn;
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    TutorInImpl tutorIn;

    @ApiOperation(value = "审核通过")
    @ResponseBody
    @GetMapping("/tutor-pass/{tid}")
    public String tutorPass(@PathVariable("tid") Integer tid){
        Tutor tutor = tutorMapper.selectById(tid);
        int info = tutorIn.passTutorInformation(tutor, true, "您的录取要求审核通过");
        if (info == 1) return "1";
        return "0";
    }

    @ResponseBody
    @ApiOperation(value = "审核不通过")
    @GetMapping("/tutor-not-pass/{tid}")
    public String tutorNotPass(@PathVariable("tid") Integer tid,@RequestParam("message") String message){
        Tutor tutor = tutorMapper.selectById(tid);
        int info = tutorIn.passTutorInformation(tutor, false, message);
        if (info == 1) return "1";
        return "0";
    }
    @ResponseBody
    @GetMapping("/grade-alert/{sid}")
    public String gradeAlert(@PathVariable("sid") Integer sid, @RequestParam("grade") String grade){
        return studentIn.updateGradeBySid(sid, grade)+"";
    }

}
