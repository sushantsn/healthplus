package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.AppointmentType;


@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {
    List<AppointmentType> findAllByOrderByName();
}
