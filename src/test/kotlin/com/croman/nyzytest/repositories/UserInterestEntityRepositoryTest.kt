package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import com.croman.nyzytest.entities.UserInterestPK
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
@TestConstructor(autowireMode = ALL)
@Sql("sql/users_interests_data.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserInterestEntityRepositoryTest(
    private val userInterestRepository: UserInterestEntityRepository,
    private val userEntityRepository: UserEntityRepository
) {

    @Test
    fun retrieve() {
        val retrievedEntity = userInterestRepository.findByIdOrNull(UserInterestPK(1, 1))!!

        retrievedEntity.weight shouldBe 5f
        retrievedEntity.featureEntity shouldBe InterestEntity("gaming", 1)
    }

    @Test
    fun retrieveUserInterests() {
        val user = userEntityRepository.findByIdOrNull(1)!!

        val userInterestEntities = user.userInterests

        userInterestEntities shouldHaveSize 1
        userInterestEntities[0].featureEntity shouldBe InterestEntity("gaming", 1)
    }
}