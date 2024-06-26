package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.InterestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InterestEntityRepository : JpaRepository<InterestEntity, Int> {
    fun findByName(name: String): InterestEntity?
}