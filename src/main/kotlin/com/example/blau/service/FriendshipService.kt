package com.example.blau.service

import com.example.blau.dto.FriendDto
import com.example.blau.dto.FriendshipRequestDto
import com.example.blau.dto.toDto
import com.example.blau.repository.FriendshipRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FriendshipService(
    private val repo: FriendshipRepository,
) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    fun createFriendshipRequest(friendshipRequestDto: FriendshipRequestDto): FriendshipRequestDto {
        log.info("Trying to create friendship request: $friendshipRequestDto")

        val existingRequest = repo.getFriendshipRequest(friendshipRequestDto.senderId, friendshipRequestDto.receiverId)
        val reverseRequest = repo.getFriendshipRequest(friendshipRequestDto.receiverId, friendshipRequestDto.senderId)

        val response = when {
            // Case 1: There's already a request
            existingRequest?.status == "pending" || existingRequest?.status == "accepted" -> {
                throw IllegalStateException("A request already exists.")
            }

            // Case 2: A reverse pending request exists → Accept the friendship
            reverseRequest?.status == "pending" -> {
                log.info("Mutual pending requests found. Accepting friendship.")
                repo.acceptFriendship(reverseRequest.requestId).toDto()
            }

            // Case 3: If the request was previously declined, allow re-sending
            existingRequest?.status == "declined" -> {
                log.info("Re-sending declined request.")
                repo.updateFriendshipRequestStatus(existingRequest.requestId, "pending").toDto()
            }

            // Case 4: No existing request → create a new pending request
            else -> repo.createFriendshipRequest(friendshipRequestDto).toDto()
        }

        log.info("Created friendship request: $response")
        return response
    }

    fun getFriendshipRequests(userId: UUID): List<FriendshipRequestDto> {
        log.info("get friendship request for user: $userId")
        val response = repo.getFriendshipRequests(userId)
        log.info("got following pending requests: $response")
        return response
    }

    fun getAllFriends(userId: UUID): List<FriendDto> =
        repo.getAllFriends(userId)
}
