package com.example.blau.service

import com.example.blau.dto.UserDto
import com.example.blau.dto.toDto
import com.example.blau.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun searchUsers(search: String?): List<UserDto> {
        return (
            if (search.isNullOrBlank()) {
                userRepository.getAllUsers()
            } else {
                userRepository.searchUsersByUsername(search)
            }
            ).map { it.toDto() }
    }
}
