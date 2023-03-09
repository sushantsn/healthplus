package com.healthplus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthplus.entity.Doctor;
import com.healthplus.services.DoctorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return  doctorService.getAllDoctors();
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteDoctor(@PathVariable Long id) {
        boolean deleted = false;
        deleted = doctorService.deleteDoctor(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = null;
        doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id,
                                                   @RequestBody Doctor doctor) {
        doctor = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(doctor);
    }

}
