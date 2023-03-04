package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByOrderByName();
}
