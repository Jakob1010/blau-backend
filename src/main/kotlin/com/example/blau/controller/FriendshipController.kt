package com.example.blau.controller

import com.example.blau.dto.FriendshipRequestDto
import com.example.blau.dto.UserDto
import com.example.blau.service.FriendshipService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/friendship")
class FriendshipController(
    private val service: FriendshipService
) {

    @PostMapping
    fun friendshipRequest(@RequestBody dto: FriendshipRequestDto): ResponseEntity<FriendshipRequestDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFriendshipRequest(dto))
    }

    @GetMapping("/request/{userId}")
    fun getFriendshipRequests(
        @PathVariable userId: UUID
    ): ResponseEntity<List<FriendshipRequestDto>> {
        return ResponseEntity.ok(service.getFriendshipRequests(userId))
    }

    @GetMapping("/{userId}")
    fun getFriendships(
        @PathVariable userId: UUID
    ): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(service.getAllFriends(userId))
    }
}
