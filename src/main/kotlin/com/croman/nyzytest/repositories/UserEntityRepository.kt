package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository : JpaRepository<UserEntity, Int> {
}