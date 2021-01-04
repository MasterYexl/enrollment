package com.yxl.enrollment.Service;

import com.yxl.enrollment.Module.MySql.Tutor;
import com.yxl.enrollment.Module.MySql.TutorInformation;

import java.util.List;

public interface TutorInformationService {
    int addTutorInformation(TutorInformation tutorInformation);

    TutorInformation getTutorInformation(int tid);

    int passTutorInformation(Tutor tutor, boolean pass, String message);

    int addTutor(Tutor tutor);

}
