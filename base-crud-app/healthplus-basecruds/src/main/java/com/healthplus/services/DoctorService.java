package com.healthplus.services;

import java.util.List;

import com.healthplus.entity.Doctor;

public interface DoctorService {
    Doctor createDoctor(Doctor employee);

    List<Doctor> getAllDoctors();

    boolean deleteDoctor(Long id);

    Doctor getDoctorById(Long id);

    Doctor updateDoctor(Long id, Doctor employee);
}
