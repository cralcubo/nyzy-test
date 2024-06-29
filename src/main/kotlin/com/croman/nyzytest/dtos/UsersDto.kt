package com.croman.nyzytest.dtos

data class UserDto(
    val id: Int? = null,
    val age: Int,
    val firstName: String,
    val lastName: String
) : Response