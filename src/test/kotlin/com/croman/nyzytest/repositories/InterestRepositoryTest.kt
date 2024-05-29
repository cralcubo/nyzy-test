package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class InterestRepositoryTest {

    @Autowired
    private lateinit var repository: InterestRepository

    @Test
    fun saveAndRetrieveInterest() {
        val i = InterestEntity(name = "football", userInterests = emptyList())
        repository.save(i)
        // retrieve it
        val retrieved = repository.findByIdOrNull(i.id)
        retrieved shouldBe i
    }

}