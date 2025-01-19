package com.example.blau.dto

import java.util.UUID

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val userId: UUID
)
