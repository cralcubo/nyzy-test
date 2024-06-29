package com.croman.nyzytest.services.comparators

import com.croman.nyzytest.entities.UserEntity

class CompatibilityUsersComparator: UsersComparator {
    private val rnd = java.util.Random()

    /**
     * Calculates how compatible 2 users are based on the features of each user
     * The value will be between 0 and 1
     */
    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return rnd.nextFloat(1f)
    }

}