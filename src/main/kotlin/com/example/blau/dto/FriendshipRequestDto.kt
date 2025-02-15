package com.example.blau.dto

import jooq.tables.pojos.Friendshiprequests
import java.time.LocalDateTime
import java.util.UUID

data class FriendshipRequestDto(
    val requestId: UUID,
    val senderId: UUID,
    val receiverId: UUID,
    val status: String?,
    val createdAt: LocalDateTime?
)

fun Friendshiprequests.toDto(): FriendshipRequestDto {
    return FriendshipRequestDto(
        requestId = requestId,
        senderId = senderId,
        receiverId = receiverId,
        status = status,
        createdAt = createdAt.toLocalDateTime()
    )
}
