package com.croman.nyzytest.services.collective.intelligence

import com.croman.nyzytest.entities.UserCharacteristicEntity
import com.croman.nyzytest.utilities.evalIf
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * The Pearson Correlation calculates
 * how correlated the points on a plane are.
 * The higher the correlation, the most aligned all the points
 * on a plane are.
 * For such calculation the values will be:
 * - 1: The highest correlation possible
 * - -1: The lowest correlation possible
 */
class PearsonCorrelation: SimilarityCalculator {

    override fun calculate(e1: Set<UserCharacteristicEntity>, e2: Set<UserCharacteristicEntity>): Float {
        // find common items and proceed with calculations
        val calculator = e1.asSequence()
            .flatMap { i1 ->
                e2.asSequence()
                    .filter { i1.characteristicId == it.characteristicId }
                    .map { i1.weight to it.weight }
            }.toList().let { Calculator(it) }

        // To calculate the correlation we should have at least
        // 2 values in the calculator (only 2 points can make a line to see if it correlates)
        return evalIf(calculator.N > 1) {
            calculator.run {
                val numerator = (sumXY - (sumX * sumY / N))
                val xDen = sumXSqr - (sumX.pow(2) / N)
                val yDen = sumYSqr - (sumY.pow(2) / N)
                numerator / sqrt(xDen * yDen)
            }
        } ?: throw IllegalArgumentException("Not enough items to calculate correlation")
    }

    private class Calculator(val xy: List<Pair<Float, Float>>) {
        val N = xy.size

        val sumXY: Float by lazy {
            xy.asSequence().map { it.first * it.second }
                .sum()
        }

        val sumX: Float by lazy {
            xy.asSequence().map { it.first }
                .sum()
        }

        val sumY: Float by lazy {
            xy.asSequence().map { it.second }
                .sum()
        }

        val sumXSqr: Float by lazy {
            xy.asSequence().map { it.first * it.first }
                .sum()
        }

        val sumYSqr: Float by lazy {
            xy.asSequence().map { it.second * it.second }
                .sum()
        }

    }


}