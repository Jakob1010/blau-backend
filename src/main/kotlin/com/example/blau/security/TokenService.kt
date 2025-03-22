package com.example.blau.security

import org.springframework.stereotype.Component
import java.util.Base64
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class TokenService {
    // todo move to properties or something else
    private val secretKey = "your-256-bit-secret-key-here"

    fun generateToken(userId: UUID): String {
        val timestamp = System.currentTimeMillis()
        val tokenData = "$userId:$timestamp"
        return signToken(tokenData)
    }

    fun validateTokenAndGetUserId(token: String): UUID? {
        val decodedToken = String(Base64.getDecoder().decode(token))
        val parts = decodedToken.split(":")
        if (parts.size != 3) return null // Ensure token format is correct

        val (userId, timestamp, signature) = parts
        val data = "$userId:$timestamp"

        // Verify signature
        val expectedSignature = generateSignature(data)
        if (expectedSignature != signature) return null

        return UUID.fromString(userId)
    }

    private fun signToken(data: String): String {
        val signature = generateSignature(data)
        val tokenWithSignature = "$data:$signature"
        return Base64.getEncoder().encodeToString(tokenWithSignature.toByteArray())
    }

    private fun generateSignature(data: String): String {
        val key = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(key)

        val signature = mac.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(signature)
    }
}
