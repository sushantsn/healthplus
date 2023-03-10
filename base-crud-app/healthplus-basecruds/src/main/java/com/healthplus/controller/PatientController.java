package com.healthplus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthplus.entity.Patient;
import com.healthplus.model.PatientViewModel;
import com.healthplus.services.DoctorService;
import com.healthplus.services.PatientService;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
    private PatientService patientService;
//
//    private DoctorService doctorService;
//
//    @Autowired
//    public PatientController(PatientService patientService, DoctorService doctorService) {
//        this.patientService = patientService;
//        this.doctorService = doctorService;
//    }


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient doctor) {
        return patientService.createPatient(doctor);
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return  patientService.getAllPatients();
    }
    
//    @GetMapping("/patient/{id}")
//    public String getPatient(@PathVariable long id, Model model) {
//        PatientViewModel patientViewModel = this.patientService.getById(id);
//
//        model.addAttribute("patientViewModel", patientViewModel);
//
//        return "patient/patient";
//    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id) {
        boolean deleted = false;
        deleted = patientService.deletePatient(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient doctor = null;
        doctor = patientService.getPatientById(id);
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                   @RequestBody Patient doctor) {
        doctor = patientService.updatePatient(id, doctor);
        return ResponseEntity.ok(doctor);
    }

}
