package com.croman.nyzytest.repositories.relationships

import com.croman.nyzytest.entities.InterestEntity
import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.entities.UserInterestEntity
import com.croman.nyzytest.repositories.InterestEntityRepository
import com.croman.nyzytest.repositories.UserEntityRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserInterestsTest {
    @Autowired
    private lateinit var userRepo: UserEntityRepository
    @Autowired
    private lateinit var interestRepo: InterestEntityRepository

    @Test
    fun save() {
        val i = InterestEntity(name = "books")
        interestRepo.save(i)
        val u = UserEntity(
            firstName = "Christian",
            lastName = "Roman",
            age = 43
        )
        userRepo.save(u)

        // creating a userInterest
        UserInterestEntity(
            user = u,
            interest = i,
            weight = 5f
        )
        





    }

}