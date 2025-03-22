package com.example.blau.dto

import jooq.tables.pojos.Users
import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val userId: UUID? = null,
    val username: String,
    val email: String? = null,
    val password: String? = null,
    val role: Role?,
    val token: String? = null,
    val tokenExpiry: LocalDateTime? = null
)

enum class Role {
    ADMIN,
    USER
}

fun Users.toDto(): UserDto {
    return UserDto(
        userId = this.userId,
        username = this.username,
        email = this.email,
        role = null,
        password = null,
        token = null,
        tokenExpiry = null
    )
}
