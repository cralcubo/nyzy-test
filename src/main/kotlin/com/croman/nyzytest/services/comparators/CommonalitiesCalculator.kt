package com.croman.nyzytest.services.comparators

import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.services.collective.similarities.SimilarityCalculator

class InterestsUsersComparator(
    private val similarityCalculator: SimilarityCalculator,
    ): UsersComparator {
    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return similarityCalculator.calculate(userA.userInterests.toSet(), userB.userInterests.toSet())
    }
}

class ValuesUsersComparator(
    private val similarityCalculator: SimilarityCalculator,
    ): UsersComparator {

    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return similarityCalculator.calculate(userA.userValues.toSet(), userB.userValues.toSet())
    }
}
