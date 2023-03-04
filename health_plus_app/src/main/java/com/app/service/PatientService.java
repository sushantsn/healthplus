package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.entities.Patient;
import com.app.model.PatientBasicViewModel;
import com.app.model.PatientViewModel;
import com.app.model.bind.EditPatientModel;
import com.app.model.bind.PatientRegistrationModel;


public interface PatientService {
    void create(PatientRegistrationModel registrationModel);

    void save(EditPatientModel editPatientModel);

    PatientViewModel getById(long id);

    Patient getByUserId(long userId);

    EditPatientModel getEditModelByUserId(long userId);

    PatientBasicViewModel getBasicById(long id);

    List<PatientBasicViewModel> getPatientsByDoctorId(long doctorId);

    Page<PatientViewModel> getPatientsByDoctorId(Pageable pageable, long doctorId);

    Page<PatientViewModel> getAll(Pageable pageable);
}
