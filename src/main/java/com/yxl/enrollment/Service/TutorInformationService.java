package com.yxl.enrollment.Service;

import com.yxl.enrollment.Module.MySql.TutorInformation;

public interface TutorInformationService {
    public int addTutorInformation(TutorInformation tutorInformation);

    TutorInformation getTutorInformation(int tid);
}
