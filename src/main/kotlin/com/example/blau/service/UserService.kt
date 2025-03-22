package com.example.blau.service

import com.example.blau.dto.UserDto
import com.example.blau.dto.toDto
import com.example.blau.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(val userRepository: UserRepository) {

    fun searchUsers(search: String?, userId: UUID): List<UserDto> {
        return userRepository.searchUsersByUsername(search, userId).map { it.toDto() }
    }
}
