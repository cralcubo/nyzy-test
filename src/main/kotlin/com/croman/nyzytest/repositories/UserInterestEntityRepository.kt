package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.UserInterestEntity
import com.croman.nyzytest.entities.UserInterestPK
import org.springframework.data.jpa.repository.JpaRepository

interface UserInterestEntityRepository : JpaRepository<UserInterestEntity, UserInterestPK>