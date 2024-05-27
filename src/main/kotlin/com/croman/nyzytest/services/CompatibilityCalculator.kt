package com.croman.nyzytest.services

import com.croman.nyzytest.entities.UserEntity

class CompatibilityCalculator: Calculator {
    private val rnd = java.util.Random()

    /**
     * Calculates how compatible 2 users are based on the characteristics of each user
     * The value will be between 0 and 1
     */
    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return rnd.nextFloat(1f)
    }

}