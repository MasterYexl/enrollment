package com.yxl.enrollment.Service.Impl;

import com.yxl.enrollment.Mapper.*;
import com.yxl.enrollment.Module.MySql.*;
import com.yxl.enrollment.Service.StudentService;
import com.yxl.enrollment.Service.TutorInformationService;
import com.yxl.enrollment.Tool.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentInImpl implements StudentService {
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    DirectionMapper directionMapper;
    @Autowired
    TutorMapper tutorMapper;

    @Override
    public int addStudentInformation(StudentInformation studentInformation) {
        return 0;
    }

    @Override
    public StudentInformation getStudentInformation(int sid) {
        return studentInformationMapper.selectBySid(sid);
    }

    @Override
    public int addStudent(Student student) {
        studentMapper.insert(student);
        StudentInformation studentInformation = new StudentInformation();
        student = studentMapper.selectByEmail(student.getEmail());
        studentInformation.setSid(student.getSid());
        return studentInformationMapper.insert(studentInformation);
    }

    @Override
    public int admission(boolean admission, StudentInformation studentInformation) {
        Message message = Factory.createMessage("录取结果",studentInformation.getGrade(),"");
        int nowProcess = studentInformation.getProcess();
        if (admission){
            Direction dir = null;
            Tutor tutor = null;
            if (nowProcess == 0){
                dir = directionMapper.selectById(studentInformation.getFirstDirection());
            }
            else if (nowProcess == 1) {
                dir = directionMapper.selectById(studentInformation.getSecondDirection());
            }
            tutor = tutorMapper.selectById(dir.getTeachers());
            studentInformation.setProcess(-nowProcess-1);
            message.setMessage("您已被录取为我院"+dir.getDirectionName()+"专业研究生，导师："+tutor.getName());
        }
        else if (nowProcess>=0){
            studentInformation.setProcess(nowProcess+1);
            if (nowProcess == 1) {
                message.setMessage("很抱歉，您在本次招生中落选");
            }
            if (nowProcess == 0){
                message.setFromEmail("录取进度通知");
                message.setMessage("您的第一志愿落选，正在进行第二志愿录取");
            }
        }
        else return -1;
        studentInformationMapper.updateProcessBySid(studentInformation.getSid(), studentInformation.getProcess());
        messageMapper.insert(message);
        return 1;
    }

    @Override
    public int updateGradeBySid(int sid, String grade) {
        Student student = studentMapper.selectById(sid);
        studentInformationMapper.updateProcessBySid(sid, 0);
        return updateGradeBySid(student, grade);
    }

    @Override
    public int updateGradeBySid(Student student, String grade) {
        StudentInformation studentInformation = studentInformationMapper.selectBySid(student.getSid());
        studentInformation.setGrade(grade);
        return updateGradeBySid(student, studentInformation);
    }

    @Override
    public int updateGradeBySid(Student student, StudentInformation studentInformation) {
        studentInformationMapper.updateBySid(studentInformation);
        Message message = Factory.createMessage("-1", student.getEmail(), "您的成绩已更新");
        return messageMapper.insert(message);

    }

}
