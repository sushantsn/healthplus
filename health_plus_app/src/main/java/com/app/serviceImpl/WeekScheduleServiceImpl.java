package com.app.serviceImpl;

import java.sql.Time;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.DayOfWeek;
import com.app.entities.DaySchedule;
import com.app.entities.WeekSchedule;
import com.app.model.bind.DayScheduleModel;
import com.app.model.bind.EditDayScheduleModel;
import com.app.model.bind.EditWeekScheduleModel;
import com.app.repository.WeekScheduleRepository;
import com.app.service.DayScheduleService;
import com.app.service.WeekScheduleService;

@Service
public class WeekScheduleServiceImpl implements WeekScheduleService {
    private WeekScheduleRepository weekScheduleRepository;

    private ModelMapper modelMapper;

    private DayScheduleService dayScheduleService;

    @Autowired
    public WeekScheduleServiceImpl(WeekScheduleRepository weekScheduleRepository, ModelMapper modelMapper,
                                   DayScheduleService dayScheduleService) {
        this.weekScheduleRepository = weekScheduleRepository;
        this.modelMapper = modelMapper;
        this.dayScheduleService = dayScheduleService;
    }

    @Override
    public WeekSchedule createDefault() {
        WeekSchedule weekSchedule = this.weekScheduleRepository.saveAndFlush(new WeekSchedule());

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            DayScheduleModel dayScheduleModel = new DayScheduleModel();
            dayScheduleModel.setDayOfWeek(dayOfWeek.toString());
            dayScheduleModel.setStartTime(Time.valueOf("00:00:00"));
            dayScheduleModel.setEndTime(Time.valueOf("00:00:00"));
            dayScheduleModel.setWeekSchedule(weekSchedule);

            this.dayScheduleService.create(dayScheduleModel);
        }

        return weekSchedule;
    }

    @Override
    public EditWeekScheduleModel getById(long id) {
        WeekSchedule weekSchedule = this.weekScheduleRepository.findById(id).orElse(null);

        EditWeekScheduleModel editWeekScheduleModel = this.modelMapper.map(weekSchedule, EditWeekScheduleModel.class);
        for (DaySchedule daySchedule : weekSchedule.getDaySchedules()) {
            EditDayScheduleModel editDayScheduleModel = this.modelMapper.map(daySchedule, EditDayScheduleModel.class);
            editDayScheduleModel.setStartTimeStr(daySchedule.getStartTime().toString());
            editDayScheduleModel.setEndTimeStr(daySchedule.getEndTime().toString());
            editWeekScheduleModel.getEditDayScheduleModels().add(editDayScheduleModel);
        }

        return editWeekScheduleModel;
    }

    @Override
    public void save(EditWeekScheduleModel editWeekScheduleModel) {
        WeekSchedule weekSchedule = this.weekScheduleRepository.getOne(editWeekScheduleModel.getId());
        weekSchedule.setAppointmentDuration(editWeekScheduleModel.getAppointmentDuration());
        this.weekScheduleRepository.saveAndFlush(weekSchedule);

        for (EditDayScheduleModel editDayScheduleModel : editWeekScheduleModel.getEditDayScheduleModels()) {
            this.dayScheduleService.save(editDayScheduleModel);
        }
    }
}
