package com.croman.nyzytest.services

import com.croman.nyzytest.entities.UserEntity

interface Calculator {
    fun calculate(userA: UserEntity, userB: UserEntity): Float
}