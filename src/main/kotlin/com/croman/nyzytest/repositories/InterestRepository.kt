package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.InterestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InterestRepository : JpaRepository<InterestEntity, Int> {
}