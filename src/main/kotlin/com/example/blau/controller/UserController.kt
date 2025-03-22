package com.example.blau.controller

import com.example.blau.dto.UserDto
import com.example.blau.security.CurrentUserId
import com.example.blau.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
class UserController(val userService: UserService) {

    @GetMapping("/search")
    fun searchUsers(
        @CurrentUserId userId: UUID,
        @RequestParam(required = false) searchQuery: String?
    ): List<UserDto> {
        return userService.searchUsers(searchQuery, userId)
    }
}
