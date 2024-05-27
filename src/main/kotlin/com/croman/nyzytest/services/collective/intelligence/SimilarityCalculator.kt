package com.croman.nyzytest.services.collective.intelligence

import com.croman.nyzytest.entities.UserCharacteristicEntity

interface SimilarityCalculator {
    /**
     * A value that indicate how close 2 set of entities are.
     * The closer they are (meaning the most similar they are)
     * the value will be: 1
     * The less close they are (meaning the least related they are)
     * the value will be: 0
     */
    fun calculate(e1: Set<UserCharacteristicEntity>, e2: Set<UserCharacteristicEntity>) : Float
}