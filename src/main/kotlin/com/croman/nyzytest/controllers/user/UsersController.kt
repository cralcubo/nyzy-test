package com.croman.nyzytest.controllers.user

import com.croman.nyzytest.dtos.Response
import com.croman.nyzytest.dtos.UserDto
import com.croman.nyzytest.dtos.utils.StatusResponseEntity.Companion.badRequest
import com.croman.nyzytest.dtos.utils.StatusResponseEntity.Companion.okRequest
import com.croman.nyzytest.exceptions.users.IllegalUserException
import com.croman.nyzytest.services.users.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

typealias ApiResponse = ResponseEntity<out Response>

@RestController
@RequestMapping("users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("{id}")
    fun getUser(@PathVariable("id") userId : String): ApiResponse {
        val id = try {
            userId.toInt()
        } catch (exception: NumberFormatException) {
            return badRequest("The user ID must be a number")
        }

        return ResponseEntity.ok(usersService.retrieveUser(id))
    }

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto) =
        ResponseEntity.ok(usersService.createUser(userDto))

    @PutMapping
    fun updateUser(@RequestBody userDto: UserDto) : ApiResponse =
        try {
            ResponseEntity.ok(usersService.updateUser(userDto))
        } catch (exception: IllegalUserException) {
            badRequest("The user to update must have an ID")
        }

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable("id") userId: String) : ApiResponse {
        val id = try {
            userId.toInt()
        } catch (exception: NumberFormatException) {
            return badRequest("The user ID must be a number")
        }

        usersService.deleteUser(id)
        return okRequest("The user $id was successfully deleted")
    }
}