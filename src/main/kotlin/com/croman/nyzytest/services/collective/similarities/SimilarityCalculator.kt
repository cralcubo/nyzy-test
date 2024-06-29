package com.croman.nyzytest.services.collective.similarities

import com.croman.nyzytest.entities.UserFeatureEntity

interface SimilarityCalculator {
    /**
     * A value that indicate how close 2 set of entities are.
     * The closer they are (meaning the most similar they are)
     * the value will be: 1
     * The less close they are (meaning the least related they are)
     * the value will be: 0
     */
    fun calculate(featuresA: Set<UserFeatureEntity>, featuresB: Set<UserFeatureEntity>) : Float
}