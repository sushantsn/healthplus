package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.WeekSchedule;


@Repository
public interface WeekScheduleRepository extends JpaRepository<WeekSchedule, Long> {
}
