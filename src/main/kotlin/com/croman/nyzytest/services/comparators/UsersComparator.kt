package com.croman.nyzytest.services.comparators

import com.croman.nyzytest.entities.UserEntity

interface UsersComparator {
    fun calculate(userA: UserEntity, userB: UserEntity): Float
}