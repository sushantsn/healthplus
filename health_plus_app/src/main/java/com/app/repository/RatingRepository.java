package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
