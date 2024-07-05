package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.entities.UserInterestEntity
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class UserEntityRepositoryTest {
    @Autowired
    private lateinit var repository: UserEntityRepository

    @Test
    fun saveAndFindByFirstName() {
        val user = UserEntity(
            age = 18,
            firstName = "Marty",
            lastName = "McFly"
            )
        val savedUser = repository.save(user)
        // retrieve the saved User
        repository.findByFirstName("Marty") shouldBe savedUser
    }

    @Test
    fun saveWithConnection() {
        val userA = UserEntity(
            age = 38,
            firstName = "John",
            lastName = "Smith",
        )
        repository.save(userA)
        val userC = UserEntity(
            age = 43,
            firstName = "Christian",
            lastName = "Roman",
        )
        repository.save(userC)

        val userB = UserEntity(
            age = 28,
            firstName = "Mary",
            lastName = "Smith",
            connections = listOf(userA, userC)
        )
        val savedUserB = repository.save(userB)

        savedUserB.connections shouldContainExactlyInAnyOrder listOf(userA, userC)
    }
}