package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.ValueEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ValueRepository : JpaRepository<ValueEntity, Int> {
}