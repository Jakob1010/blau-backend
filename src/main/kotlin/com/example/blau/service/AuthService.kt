package com.example.blau.service

import com.example.blau.dto.RegisterDto
import com.example.blau.dto.UserDto
import com.example.blau.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(username: String, password: String): String? {
        val user = userRepository.findByUsername(username) ?: return null

        if (!passwordEncoder.matches(password, user.password)) {
            return null
        }

        val token = generateToken()
        val expiry = LocalDateTime.now().plusDays(7)
        userRepository.updateToken(user.userId!!, token, expiry)

        return token
    }

    fun register(request: RegisterDto): Boolean {
        if (userRepository.findByUsername(request.username) != null) {
            return false
        }

        val hashedPassword = passwordEncoder.encode(request.password)

        return try {
            userRepository.save(UserDto(username = request.username, password = hashedPassword, email = request.email))
            true
        } catch (e: Exception) {
            false
        }
    }

    fun validateToken(token: String): UserDto? {
        return userRepository.findByToken(token)
    }

    private fun generateToken(): String {
        return UUID.randomUUID().toString()
    }
}
