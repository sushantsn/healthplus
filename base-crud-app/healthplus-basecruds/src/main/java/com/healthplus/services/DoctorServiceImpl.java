package com.healthplus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.healthplus.entity.Doctor;
import com.healthplus.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        Doctor doctorEntity = new Doctor();

        BeanUtils.copyProperties(doctor, doctorEntity);
        doctorRepository.save(doctorEntity);
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorEntities
                = doctorRepository.findAll();
/*		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.startPracticeDate = startPracticeDate;
		this.location = location;
		this.address = address;
		this.description = description;
		this.picturePath = picturePath;
		this.patients = patients;
		this.appointments = appointments;*/
        List<Doctor> doctors = doctorEntities
                .stream()
                .map(emp -> new Doctor(
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId(),
                        emp.getGender(),
                        emp.getTelephoneNumber(),
                        emp.getDateOfBirth(),
                        emp.getStartPracticeDate(),
                        emp.getLocation(),
                        emp.getAddress(),
                        emp.getDescription(),
                        emp.getPicturePath(),
                        emp.getPatients(), 
                        emp.getAppointments()))
                .collect(Collectors.toList());
        return doctors;
    }

    @Override
    public boolean deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        doctorRepository.delete(doctor);
        return true;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Doctor doctorEntity
                = doctorRepository.findById(id).get();
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorEntity, doctor);
        return doctor;
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor doctorEntity
                = doctorRepository.findById(id).get();
        doctorEntity.setEmailId(doctor.getEmailId());
        doctorEntity.setFirstName(doctor.getFirstName());
        doctorEntity.setLastName(doctor.getLastName());

        doctorRepository.save(doctorEntity);
        return doctor;
    }
}
