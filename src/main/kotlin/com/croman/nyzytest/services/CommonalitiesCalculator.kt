package com.croman.nyzytest.services

import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.services.collective.intelligence.SimilarityCalculator

class InterestsCalculator(private val similarityCalculator: SimilarityCalculator): Calculator {
    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return similarityCalculator.calculate(userA.userInterests.toSet(), userB.userInterests.toSet())
    }
}

class ValuesCalculator(private val similarityCalculator: SimilarityCalculator): Calculator {
    override fun calculate(userA: UserEntity, userB: UserEntity): Float {
        return similarityCalculator.calculate(userA.userValues.toSet(), userB.userValues.toSet())
    }
}
