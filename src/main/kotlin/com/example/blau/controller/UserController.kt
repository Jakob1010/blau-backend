package com.example.blau.controller

import com.example.blau.dto.UserDto
import com.example.blau.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(val userService: UserService) {

    @GetMapping("/search")
    fun searchUsers(
        @RequestParam(required = false) searchQuery: String?
    ): List<UserDto> {
        return userService.searchUsers(searchQuery)
    }
}
