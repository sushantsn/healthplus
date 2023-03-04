package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Log;


@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
