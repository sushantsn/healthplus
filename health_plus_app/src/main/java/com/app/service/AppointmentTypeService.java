package com.app.service;

import java.util.List;

import com.app.entities.AppointmentType;
import com.app.model.AppointmentTypeViewModel;


public interface AppointmentTypeService {
    List<AppointmentTypeViewModel> getAll();

    AppointmentType getById(long id);
}
