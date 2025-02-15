package com.example.blau.controller

import com.example.blau.dto.UserDto
import com.example.blau.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @GetMapping("/users/search")
    fun searchUsers(
        @RequestParam(required = false) search: String?
    ): List<UserDto> {
        return userService.searchUsers(search)
    }
}
