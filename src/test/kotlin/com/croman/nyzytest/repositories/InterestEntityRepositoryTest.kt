package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.InterestEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class InterestEntityRepositoryTest {

    @Autowired
    private lateinit var repository: InterestEntityRepository

    @Test
    fun saveAndRetrieveInterest() {
        val i = InterestEntity(name = "football")
        repository.save(i)
        // retrieve it
        val retrieved = repository.findByIdOrNull(i.id)
        retrieved shouldBe i
    }

    @Test
    fun save20Interests() {
        val humanInterests = listOf(
            "Movies",
            "Books",
            "Sports",
            "Cooking",
            "Traveling",
            "Music",
            "Art",
            "Photography",
            "Gardening",
            "Technology",
            "Fitness",
            "Fashion",
            "Gaming",
            "Dancing",
            "Writing",
            "Crafting",
            "Hiking",
            "Yoga",
            "Volunteering",
            "Fishing"
        )

        humanInterests.forEach { interest ->
            val i = InterestEntity(name = interest)
            repository.save(i)
        }

    }

}