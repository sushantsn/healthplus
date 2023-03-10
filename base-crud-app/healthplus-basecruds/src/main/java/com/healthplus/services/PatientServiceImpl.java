package com.healthplus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//import com.app.service.UserService;
import com.healthplus.entity.Patient;
import com.healthplus.model.PatientViewModel;
import com.healthplus.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

//    private UserService userService;

    private DoctorService doctorService;



    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        Patient patientEntity = new Patient();

        BeanUtils.copyProperties(patient, patientEntity);
        patientRepository.save(patientEntity);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patientEntities
                = patientRepository.findAll();

        List<Patient> patients = patientEntities
                .stream()
                .map(emp -> new Patient(
                        emp.getAadhaar(), 
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId(), 
                        emp.getDateOfBirth(),
                        emp.getTelephoneNumber(),
                        emp.getDoctor()))
                .collect(Collectors.toList());
        return patients;
    }

    @Override
    public boolean deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).get();
        patientRepository.delete(patient);
        return true;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patientEntity
                = patientRepository.findById(id).get();
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientEntity, patient);
        return patient;
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient patientEntity
                = patientRepository.findById(id).get();
        patientEntity.setEmailId(patient.getEmailId());
        patientEntity.setFirstName(patient.getFirstName());
        patientEntity.setLastName(patient.getLastName());

        patientRepository.save(patientEntity);
        return patient;
    }

    @Override
    public PatientViewModel getById(long id) {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        return this.modelMapper.map(patient, PatientViewModel.class);
    }
}
