package com.app.service;

import com.app.entities.WeekSchedule;
import com.app.model.bind.EditWeekScheduleModel;


public interface WeekScheduleService {
    EditWeekScheduleModel getById(long id);

    WeekSchedule createDefault();

    void save(EditWeekScheduleModel editWeekScheduleModel);
}
