package com.yxl.enrollment.Service.Impl;

import com.yxl.enrollment.Mapper.DirectionMapper;
import com.yxl.enrollment.Mapper.MessageMapper;
import com.yxl.enrollment.Mapper.TutorInformationMapper;
import com.yxl.enrollment.Mapper.TutorMapper;
import com.yxl.enrollment.Module.MySql.Direction;
import com.yxl.enrollment.Module.MySql.Message;
import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;
import com.yxl.enrollment.Service.TutorInformationService;
import com.yxl.enrollment.Tool.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorInImpl implements TutorInformationService {
    @Autowired
    TutorMapper tutorMapper;
    @Autowired
    TutorInformationMapper tutorInformationMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    DirectionImpl directionImpl;

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
        if (tutorInformation==null) tutorInformation = new TutorInformation();
        return tutorInformation;
    }

    @Override
    public int passTutorInformation(Tutor tutor, boolean pass, String message) {
        int p = pass? 1:0;
        tutorInformationMapper.updateDisplayByTid(tutor.getTid(), p);
        TutorInformation tutorInformation = tutorInformationMapper.selectByTid(tutor.getTid());
        Message msg = Factory.createMessage("-1", tutor.getEmail(),message);
        messageMapper.insert(msg);
        Direction direction = directionImpl.getDirectionByDirectionName(tutorInformation.getDirection());
        //将通过的研究方向添加到表
        if (pass){
            if (direction == null) {
                direction = new Direction();
                direction.setTeachers(tutor.getTid());
                direction.setDirectionName(tutorInformation.getDirection());
                return directionImpl.addDirection(direction);
            }
            else return 1;
        }
        else return directionImpl.deleteByDid(direction.getDid());
        //else 新增修改任务
    }

    @Override
    public int addTutor(Tutor tutor) {
        return tutorMapper.insert(tutor);
    }
}
