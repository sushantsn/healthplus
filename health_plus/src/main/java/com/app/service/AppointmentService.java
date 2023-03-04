package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.AppointmentDateViewModel;
import com.app.model.AppointmentViewModel;
import com.app.model.bind.AddAppointmentModel;

public interface AppointmentService {
    void save(AddAppointmentModel addAppointmentModel);

    List<AppointmentDateViewModel> getAllForDateAndDoctor(Date date, long doctorId);

    List<AppointmentViewModel> getAllForPatientById(long patientId);

    Page<AppointmentViewModel> getAllForPatientById(long patientId, Pageable pageable);

    List<AppointmentViewModel> getAllForDoctorById(long doctorId);

    Page<AppointmentViewModel> getAllForDoctorById(long doctorId, Pageable pageable);

    AppointmentViewModel getByDateAndDoctorId(Date date, long doctorId);

    AppointmentViewModel getById(long id);
}
