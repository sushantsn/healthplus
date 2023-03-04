package com.app.service;

import com.app.model.bind.DayScheduleModel;
import com.app.model.bind.EditDayScheduleModel;


public interface DayScheduleService {
    void create(DayScheduleModel dayScheduleModel);

    void save(EditDayScheduleModel editDayScheduleModel);
    //getById(long id);
}
