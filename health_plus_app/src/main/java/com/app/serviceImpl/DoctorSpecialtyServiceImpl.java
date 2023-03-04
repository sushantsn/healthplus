package com.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.DoctorSpecialtyRepository;
import com.app.service.DoctorSpecialtyService;

@Service
public class DoctorSpecialtyServiceImpl implements DoctorSpecialtyService {
    private DoctorSpecialtyRepository doctorSpecialtyRepository;

    @Autowired
    public DoctorSpecialtyServiceImpl(DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }
}
