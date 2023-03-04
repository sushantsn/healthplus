package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findOneByUserId(long userId);

    List<Doctor> findAllByOrderByFirstNameAscLastName();
}
