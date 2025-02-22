package com.example.blau.dto

import java.util.UUID

data class FriendDto(
    val userId: UUID,
    val username: String,
    val email: String
)
