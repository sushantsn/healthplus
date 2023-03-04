package com.app.serviceImpl;

import java.sql.Time;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.DaySchedule;
import com.app.model.bind.DayScheduleModel;
import com.app.model.bind.EditDayScheduleModel;
import com.app.repository.DayScheduleRepository;
import com.app.service.DayScheduleService;

@Service
public class DayScheduleServiceImpl implements DayScheduleService {
    private DayScheduleRepository dayScheduleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public DayScheduleServiceImpl(DayScheduleRepository dayScheduleRepository, ModelMapper modelMapper) {
        this.dayScheduleRepository = dayScheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(DayScheduleModel dayScheduleModel) {
        DaySchedule daySchedule = this.modelMapper.map(dayScheduleModel, DaySchedule.class);

        this.dayScheduleRepository.saveAndFlush(daySchedule);
    }

    @Override
    public void save(EditDayScheduleModel editDayScheduleModel) {
        DaySchedule daySchedule = this.dayScheduleRepository.getOne(editDayScheduleModel.getId());
        // Convert from string "hh:mm" to time(hh:mm:ss) // welcome to Java
        String startTimeFormat = editDayScheduleModel.getStartTimeStr() + (editDayScheduleModel.getStartTimeStr().length() == 5 ? ":00" : "");
        String endTimeFormat = editDayScheduleModel.getEndTimeStr() + (editDayScheduleModel.getEndTimeStr().length() == 5 ? ":00" : "");

        daySchedule.setStartTime(Time.valueOf(startTimeFormat));
        daySchedule.setEndTime(Time.valueOf(endTimeFormat));

        this.dayScheduleRepository.saveAndFlush(daySchedule);
    }
}
