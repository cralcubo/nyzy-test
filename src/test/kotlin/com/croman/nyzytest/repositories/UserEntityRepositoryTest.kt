package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.UserEntity
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
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
            firstName = "Marty",
            lastName = "McFly"
            )
        repository.save(user)
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
        repository.save(userB)
    }

    @Test
    @Transactional
    fun retrieveUser() {
        // user 4 is mary & user 3 is john
        val mary = repository.findByFirstName("Mary")!!
        val john = repository.findByFirstName("John")!!
        val chris = repository.findByFirstName("Christian")!!

        mary.lastName shouldBe "Smith"
        mary.connections shouldContainExactlyInAnyOrder listOf(john, chris)
        mary.connections shouldHaveSize 2
    }

    @Test
    fun create100Users() {
        val names =
            listOf("Homer", "Bart", "Lisa", "Marge", "Maggie", "Moe", "Ned", "Barney", "Patty", "Selma")
        val lastNames =
            listOf("Simpson", "Thompson", "Atreides", "Blaine", "McArthur", "Schrader", "Stark", "Stinson", "Lannister", "Targaryen")

        // create 100 users combining first and last names where age is a random number between 18 and 81
        for(lastName in lastNames) {
            for(firstName in names) {
                val user = UserEntity(
                    age = (18..81).random(),
                    firstName = firstName,
                    lastName = lastName
                )
                repository.save(user)
            }
        }
    }
}