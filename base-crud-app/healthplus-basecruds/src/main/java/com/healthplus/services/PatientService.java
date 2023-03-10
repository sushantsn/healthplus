package com.healthplus.services;

import java.util.List;

import com.healthplus.entity.Patient;

public interface PatientService {
    Patient createPatient(Patient employee);

    List<Patient> getAllPatients();

    boolean deletePatient(Long id);

    Patient getPatientById(Long id);

    Patient updatePatient(Long id, Patient employee);
}
