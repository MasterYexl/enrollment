package com.yxl.enrollment.Controller;

import com.alibaba.fastjson.JSON;
import com.yxl.enrollment.Conponent.Check;
import com.yxl.enrollment.Mapper.MessageMapper;
import com.yxl.enrollment.Module.MySql.Message;
import com.yxl.enrollment.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/message")
@Controller
public class MessageController {
    @Autowired
    Check check;
    @Autowired
    MessageMapper messageMapper;

    @ResponseBody
    @GetMapping("/get")
    public String getMessage(HttpSession session){
        List<Message> messages = messageMapper.selectAllByEmail(Tool.getEmail(session));
        return JSON.toJSONString(messages);
    }

    @ResponseBody
    @GetMapping("/get-new")
    public String getNewMessage(HttpSession session){
        List<Message> messages = messageMapper.selectNewByEmail(Tool.getEmail(session));
        return JSON.toJSONString(messages);
    }

    @ResponseBody
    @GetMapping("/all-new-read")
    public String setOldMessage(HttpSession session){
        messageMapper.readAllNewByEmail(Tool.getEmail(session));
        return "1";
    }
    

    @GetMapping("/page")
    public String getPage(HttpSession session, Model model){
        List<Message> messages = messageMapper.selectAllByEmail(Tool.getEmail(session));
        model.addAttribute("messages", messages);
        return "Module/messages";
    }
}
