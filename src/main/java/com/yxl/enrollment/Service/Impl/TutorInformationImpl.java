package com.yxl.enrollment.Service.Impl;

import com.yxl.enrollment.Mapper.MessageMapper;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Module.MySql.Message;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Service.TutorInformationService;
import com.yxl.enrollment.Tool.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorInformationImpl implements TutorInformationService {
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    MessageMapper messageMapper;

    //添加导师信息,并通知管理员审核
    @Override
    public int addTutorInformation(TutorInformation tutorInformation) {
        TutorInformation orgInfo = getTutorInformation(tutorInformation.getTid());
        if (orgInfo.equals(tutorInformation)) return -1;
        String msg = "一条教师信息待审核";
        if (orgInfo.getTid() == tutorInformation.getTid()) {
            tutorInformation.setTutorInfoId(orgInfo.getTutorInfoId());
            tutorInformationMapper.updateByPrimaryKey(tutorInformation);
            msg = "一条教师更新信息待审核";
        }
        else tutorInformationMapper.insert(tutorInformation);
        Message message = Factory.createMessage(tutorInformation.getEmail() + "", "-1", msg);
        return messageMapper.insert(message);
    }

    @Override
    public TutorInformation getTutorInformation(int tid) {
        TutorInformation tutorInformation = tutorInformationMapper.selectByTid(tid);
        return tutorInformation;
    }
}
