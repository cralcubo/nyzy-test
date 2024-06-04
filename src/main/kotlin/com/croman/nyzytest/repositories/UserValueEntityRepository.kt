package com.croman.nyzytest.repositories;

import com.croman.nyzytest.entities.UserValueEntity
import com.croman.nyzytest.entities.UserValuePK
import org.springframework.data.jpa.repository.JpaRepository

interface UserValueEntityRepository : JpaRepository<UserValueEntity, UserValuePK>