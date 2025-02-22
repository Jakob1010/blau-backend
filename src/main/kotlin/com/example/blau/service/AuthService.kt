package com.example.blau.service

import com.example.blau.dto.RegisterDto
import com.example.blau.dto.Role
import com.example.blau.dto.TokenInfo
import com.example.blau.dto.UserDto
import com.example.blau.repository.UserRepository
import com.example.blau.security.TokenService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService,
) {
    fun login(username: String, password: String): TokenInfo? {
        val user = userRepository.findByUsername(username) ?: return null

        if (!passwordEncoder.matches(password, user.password)) {
            return null
        }

        val token = tokenService.generateToken(user.userId!!)
        val expiry = LocalDateTime.now().plusDays(7)
        userRepository.updateToken(user.userId, token, expiry)

        return TokenInfo(token, user.userId, user.username, user.role)
    }

    fun register(request: RegisterDto): Boolean {
        if (userRepository.findByUsername(request.username) != null) {
            return false
        }

        val hashedPassword = passwordEncoder.encode(request.password)

        userRepository.save(
            UserDto(
                username = request.username,
                password = hashedPassword,
                email = request.email,
                role = Role.USER
            )
        )
        return true
    }

    fun validateToken(token: String): TokenInfo? {
        // First validate token signature and get userId
        val userId = tokenService.validateTokenAndGetUserId(token) ?: return null

        // Then check if token exists in DB and isn't expired
        val user = userRepository.findByToken(token) ?: return null
        if (user.tokenExpiry?.isBefore(LocalDateTime.now()) == true) {
            return null
        }

        // Verify that the userId in token matches the user from DB
        if (user.userId != userId) {
            return null
        }

        return TokenInfo(token, user.userId, user.username, user.role)
    }
}
