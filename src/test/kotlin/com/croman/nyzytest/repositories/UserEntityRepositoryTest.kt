package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.UserEntity
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserEntityRepositoryTest {
    @Autowired
    private lateinit var repository: UserEntityRepository

    @Test
    fun save() {
        val user = UserEntity(
            age = 18,
            firstName = "Peter",
            lastName = "Cohl",
            userInterests = emptyList(),
            userValues = emptyList(),
            connections = emptyList()
            )
        repository.save(user)
    }

    @Test
    fun saveWithConnection() {
        val userA = UserEntity(
            age = 38,
            firstName = "John",
            lastName = "Smith",
            userInterests = listOf(),
            userValues = emptyList(),
            connections = emptyList()
        )
        repository.save(userA)

        val userB = UserEntity(
            age = 28,
            firstName = "Mary",
            lastName = "Smith",
            userInterests = listOf(),
            userValues = emptyList(),
            connections = listOf(userA)
        )

        repository.save(userB)
    }

    @Test
    fun retrieveUser() {
        // user 4 is mary & user 3 is john
        val mary = repository.findByFirstName("Mary")!!
        val john = repository.findByFirstName("John")!!

        mary.lastName shouldBe "Smith"
        mary.connections shouldContain john
        john.connections shouldHaveSize 0
    }

}