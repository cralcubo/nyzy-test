package com.croman.nyzytest.controllers.user

import com.croman.nyzytest.dtos.UserDto
import com.croman.nyzytest.services.users.UsersService
import org.junit.jupiter.api.Nested
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@WebMvcTest(UsersController::class)
class UsersControllerTest(
    private val usersController: UsersController,
    @MockBean val usersService: UsersService,
    private val mockMvc: MockMvc
) {

    private val mockUser = UserDto(
        id = 1,
        firstName = "Chris",
        lastName = "Roman",
        age = 43
    )

    @Nested
    inner class GetTests {

        fun getUser() {
            whenever(usersService.retrieveUser(1)).thenReturn(mockUser)

            mockMvc.get("/users/{id}", "1")
                .andExpect {
                    status { isOk() }
                }
        }
    }

    @Nested
    inner class PostTests {}

    @Nested
    inner class PutTests {}

    @Nested
    inner class DeleteTests {}

}