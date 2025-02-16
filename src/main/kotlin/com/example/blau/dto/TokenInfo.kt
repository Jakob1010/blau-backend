package com.example.blau.dto

import java.util.UUID

data class TokenInfo(
    val token: String,
    val userId: UUID,
    val username: String,
    val role: Role? = null,
)
