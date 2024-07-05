package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL

@DataJpaTest
@TestConstructor(autowireMode = ALL)
class InterestEntityRepositoryTest(private val repository: InterestEntityRepository) {

    @Test
    fun saveAndRetrieveInterest() {
        val interest = InterestEntity(name = "football")
        val savedInterest = repository.save(interest)
        // retrieve it
        repository.findByName("football") shouldBe savedInterest
    }

    @Test
    fun retrieveNotFound() {
        repository.findByName("test") shouldBe null
    }
}