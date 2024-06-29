package com.croman.nyzytest.services.collective.similarities

import com.croman.nyzytest.entities.UserFeatureEntity
import kotlin.math.sqrt

/**
 * It takes 2 items between N entities
 * to find how close those entities are between those items.
 *
 * The closer the entities are, the more similar they are.
 *
 * To calculate how close the entities are we use the Pythagorean Theorem
 * (hypotenuse of a triangle)
 */
class EuclideanDistance(private val minSize: Int = 10) : SimilarityCalculator {
    /**
     * Calculates the Euclidean distance between 2 entities
     * based on the common items they have with each other
     */
    override fun calculate(featuresA: Set<UserFeatureEntity>, featuresB: Set<UserFeatureEntity>): Float {
        val commonItems = featuresA.asSequence()
            .flatMap { fa ->
                featuresB.asSequence()
                    .filter { fa.featureEntity.id == it.featureEntity.id }
                    .map { fa to it }
            }
            .toList()
        if(commonItems.size < minSize) {
            return 0f
        }

        // find common items and calc Sum(i2 - i1)^2
        val sumPows = commonItems.map { it.first.weight - it.second.weight }
            .map { it * it }
            .sum()

        return 1 / (1 + sqrt(sumPows))
    }
}