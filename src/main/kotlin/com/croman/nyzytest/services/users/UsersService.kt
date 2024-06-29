package com.croman.nyzytest.services.users

import com.croman.nyzytest.dtos.UserDto
import com.croman.nyzytest.dtos.mappers.toDto
import com.croman.nyzytest.dtos.mappers.toEntity
import com.croman.nyzytest.entities.UserEntity
import com.croman.nyzytest.exceptions.users.IllegalUserException
import com.croman.nyzytest.repositories.UserEntityRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val userRepository: UserEntityRepository) {

    fun retrieveUser(userId: Int) =
        userRepository.getReferenceById(userId)
            .toDto()

    fun deleteUser(userId: Int) =
        userRepository.deleteById(userId)

    fun createUser(userDto: UserDto) =
        userRepository.save(userDto.toEntity())
            .toDto()

    @Throws(IllegalUserException::class)
    fun updateUser(userDto: UserDto): UserDto {
        if (userDto.id == null) {
            throw IllegalUserException("The user is missing an ID. User cannot be updated")
        }
        return userRepository.save(userDto.toEntity())
            .toDto()
    }

}