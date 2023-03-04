package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.DaySchedule;


@Repository
public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {
}
