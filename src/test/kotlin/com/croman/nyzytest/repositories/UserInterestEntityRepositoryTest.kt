package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.entities.UserInterestEntity
import com.croman.nyzytest.entities.UserInterestPK
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import kotlin.random.Random

@SpringBootTest
class UserInterestEntityRepositoryTest {
    @Autowired
    private lateinit var userInterestEntityRepository : UserInterestEntityRepository
    @Autowired
    private lateinit var repoUser : UserEntityRepository
    @Autowired
    private lateinit var repoInterest : InterestEntityRepository

    @Test
    fun save() {
        val user = UserEntity(
            age = 43,
            firstName = "Christian",
            lastName = "Roman"
        )
        val interest = InterestEntity(name = "music")
        repoUser.save(user)
        repoInterest.save(interest)

        val userInterest = UserInterestEntity(
            weight = 1.0f,
            user = user,
            featureEntity = interest
        )
        userInterestEntityRepository.save(userInterest)
    }

    @Test
    fun retrieve() {
        val user = repoUser.findByFirstName("Christian")!!
        val interest = repoInterest.findByName("music")!!
        val userInterest = userInterestEntityRepository.findByIdOrNull(UserInterestPK(user.id!!, interest.id!!))!!

        userInterest.weight shouldBe 1.0f
        userInterest.featureEntity shouldBe interest
    }

    @Test
    @Transactional
    fun retrieveAll() {
//        val user = repoUser.findByFirstName("John")!!
//        listOf("games", "food", "movies", "books", "concerts")
//            .map { InterestEntity(it) }
//            .forEach {
//                val saved = repoInterest.save(it)
//                userInterestEntityRepository.save(UserInterestEntity(1.0f, user, saved))
//            }
//
//        repoInterest.findAll() shouldHaveSize 5
//        userInterestEntityRepository.findAll() shouldHaveSize 5

        val interests = repoUser.findByFirstName("John")!!.userInterests
            .map { it.featureEntity }
            .map { it.name }


        interests shouldHaveSize 5
        interests shouldContainExactlyInAnyOrder listOf("games", "food", "movies", "books", "concerts")
    }

    @Test
    fun createUsersInterests() {
        // collect all users and interests
        val interests = repoInterest.findAll()
        repoUser.findAll().forEach { user ->
            interests.shuffled().subList(0, Random.nextInt(interests.size)).forEach { interest ->
//                println("${user.firstName}  ${user.lastName} prefer ${interest.name} with a weight of ${Random.nextFloat()}")
                val userInterest = UserInterestEntity(
                    weight = Random.nextFloat(),
                    user = user,
                    featureEntity = interest
                )
                userInterestEntityRepository.save(userInterest)
            }

        }
    }


}