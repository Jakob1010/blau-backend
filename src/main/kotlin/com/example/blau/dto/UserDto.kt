package com.example.blau.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val userId: UUID?,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
    val token: String?,
    val tokenExpiry: LocalDateTime?
)
