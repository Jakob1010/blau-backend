package com.example.blau.security

import com.example.blau.service.AuthService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class TokenAuthenticationService(
    private val authService: AuthService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")?.removePrefix("Bearer ")

        if (!token.isNullOrEmpty()) {
            // Use the authService to validate the token and get user info
            val tokenInfo = authService.validateToken(token)
            if (tokenInfo != null) {
                val authentication = UsernamePasswordAuthenticationToken(
                    tokenInfo, // Store the TokenInfo as principal
                    null,
                    listOf(SimpleGrantedAuthority(tokenInfo.role!!.name))
                ).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }
}
