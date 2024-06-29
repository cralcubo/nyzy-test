package com.croman.nyzytest.dtos.mappers

import com.croman.nyzytest.dtos.UserDto
import com.croman.nyzytest.entities.UserEntity

fun UserEntity.toDto() : UserDto {
    require(id != null) { "User unexpectedly does not have an ID" }
    return UserDto(
        id = this.id,
        age = age,
        firstName = firstName,
        lastName = lastName
    )
}

fun UserDto.toEntity() =
    UserEntity(
        firstName = firstName,
        lastName = lastName,
        age = age
        )