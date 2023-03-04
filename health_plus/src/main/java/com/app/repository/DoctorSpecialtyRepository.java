package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.DoctorSpecialty;


@Repository
public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, Long> {
}
