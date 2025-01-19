package com.example.blau.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val userId: UUID? = null,
    val username: String,
    val email: String? = null,
    val password: String,
    val role: Role,
    val token: String? = null,
    val tokenExpiry: LocalDateTime? = null
)

enum class Role {
    ADMIN,
    USER
}
