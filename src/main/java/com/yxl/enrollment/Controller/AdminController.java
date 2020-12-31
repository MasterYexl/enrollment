package com.yxl.enrollment.Controller;

import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Module.MySql.Admin;
import com.yxl.enrollment.Module.SignState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    Check check;



}
