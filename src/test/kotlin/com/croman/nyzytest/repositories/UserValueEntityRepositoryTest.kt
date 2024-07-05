package com.croman.nyzytest.repositories

import com.croman.nyzytest.entities.*
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import kotlin.random.Random

@SpringBootTest
class UserValueEntityRepositoryTest {
    @Autowired
    private lateinit var repository: UserValueEntityRepository

    @Autowired
    private lateinit var userRepository: UserEntityRepository

    @Autowired
    private lateinit var valueRepository: ValueEntityRepository

    @Test
    fun save() {
        val user = UserEntity(
            age = 28,
            firstName = "Marcelo",
            lastName = "Roman")
        userRepository.save(user)


        val valuesName = listOf("integrity", "kindness", "compassion", "respect", "responsibility", "pride")

        valuesName.forEach { value ->
            val valueEntity = ValueEntity(name = value)
            valueRepository.save(valueEntity)

            val entity = UserValueEntity(1.0f, user, valueEntity)
            repository.save(entity)
        }
    }

    @Test
    fun retrieve() {
        val user = userRepository.findByFirstName("Marcelo")!!
        val value = valueRepository.findByName("integrity")!!
        val entity = repository.findByIdOrNull(UserValuePK(user.id!!, value.id!!))!!

        entity.featureEntity shouldBe value
        entity.weight shouldBe 1.0f
    }

    @Test
    @Transactional
    fun retrieveByUser() {
//        val user = userRepository.save(
//            UserEntity(
//            age = 43,
//            firstName = "Freddy",
//            lastName = "Kruger")
//        )
//
//        listOf("integrity", "kindness", "compassion", "respect", "responsibility", "pride")
//            .forEach {
//                val valueEntity = valueRepository.save(ValueEntity(name = it))
//                repository.save(UserValueEntity(1.0f, user, valueEntity))
//            }

        val user = userRepository.findByIdOrNull(1)
        user shouldNotBe null

        val values = user!!.userValues

        values shouldHaveSize 6
        values.map { it.featureEntity.name } shouldContainExactlyInAnyOrder listOf("integrity", "kindness", "compassion", "respect", "responsibility", "pride")
    }


    @Test
    fun createUsersValues() {
        // collect all users and values
        val values = valueRepository.findAll()
        userRepository.findAll().forEach { user ->
            values.shuffled().subList(0, Random.nextInt(values.size)).forEach { value ->
                repository.save(UserValueEntity(Random.nextFloat(), user, value))
            }
        }
    }

}