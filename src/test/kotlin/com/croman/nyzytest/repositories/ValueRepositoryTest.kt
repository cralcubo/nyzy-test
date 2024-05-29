package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.ValueEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ValueRepositoryTest {
    @Autowired
    private lateinit var repository: ValueRepository

    @Test
    fun saveAndRetrieve() {
        val v = ValueEntity(name = "family", userValues = emptyList())
        repository.save(v)

        // retrieve
        repository.findByIdOrNull(v.id) shouldBe v
    }
}