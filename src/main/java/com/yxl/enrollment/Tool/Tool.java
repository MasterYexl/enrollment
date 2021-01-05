package com.yxl.enrollment.Tool;

import com.yxl.enrollment.Module.MySql.Direction;
import com.yxl.enrollment.Module.MySql.User;
import com.yxl.enrollment.Module.SignState;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

public class Tool {
    public static void setMsg(Model model, HttpSession session){
        model.addAttribute("msg",session.getAttribute("msg"));
        session.removeAttribute("msg");
    }
    public static String getEmail(HttpSession session){
        SignState signState = (SignState) session.getAttribute("signState");
        User user = signState.getUser();
        return user.getEmail();
    }
//    public static List<Direction> parseDirection(List<Direction> directions){
//        List<Direction> newDirection = new LinkedList<>();
//        for (Direction direction:directions){
//            String[] tutors = direction.getTeachers().split(" ");
//            for (String tutor:tutors){
//                if (!tutor.equals("")){
//                    Direction tmpDirection = new Direction();
//                    tmpDirection.setTeachers(tutor);
//                    tmpDirection.setDirectionName(direction.getDirectionName());
//                    tmpDirection.setDid();
//                }
//            }
//        }
//    }
}
