package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findOneByUserId(long userId);

    List<Patient> findAllByDoctorId(long doctorId);

    Page<Patient> findAllByDoctorIdOrderByDateOfBirthDesc(long doctorId, Pageable pageable);
}
