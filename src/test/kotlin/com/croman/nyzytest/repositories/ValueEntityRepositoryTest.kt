package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.ValueEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ValueEntityRepositoryTest {
    @Autowired
    private lateinit var repository: ValueEntityRepository

    @Test
    fun saveAndRetrieve() {
        val v = ValueEntity(name = "family")
        repository.save(v)

        // retrieve
        repository.findByIdOrNull(v.id) shouldBe v
    }

    @Test
    fun save20Values() {
        val humanValues = listOf(
            "Honesty",
            "Integrity",
            "Respect",
            "Compassion",
            "Kindness",
            "Responsibility",
            "Fairness",
            "Generosity",
            "Courage",
            "Humility",
            "Loyalty",
            "Patience",
            "Perseverance",
            "Gratitude",
            "Forgiveness",
            "Trustworthiness",
            "Self-discipline",
            "Empathy",
            "Inclusivity",
            "Wisdom"
        )

        humanValues.forEach { value ->
            val v = ValueEntity(name = value)
            repository.save(v)
        }
    }
}