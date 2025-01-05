package com.example.blau.controller

import com.example.blau.dto.LoginRequest
import com.example.blau.dto.LoginResponse
import com.example.blau.dto.RegisterDto
import com.example.blau.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
private class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = authService.login(request.username, request.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity.ok(LoginResponse(token))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterDto): ResponseEntity<Unit> {
        return if (authService.register(request)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}
