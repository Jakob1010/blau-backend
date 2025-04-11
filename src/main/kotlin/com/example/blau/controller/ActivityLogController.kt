package com.example.blau.controller

import com.example.blau.dto.ActivityLogDto
import com.example.blau.service.ActivityLogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

@RestController
@RequestMapping("/api/v1/log")
class ActivityLogController(private val service: ActivityLogService) {

    @PostMapping
    fun createActivityLog(@RequestBody dto: ActivityLogDto): ResponseEntity<ActivityLogDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createActivityLog(dto))
    }

    @GetMapping("/{userId}")
    fun getActivityLogsByUserId(
        @PathVariable userId: UUID,
        @RequestParam(required = false) activityIds: List<UUID>?
    ): ResponseEntity<List<ActivityLogDto>> {
        val activityLogs = service.getActivityLogsByUserId(userId, activityIds)
        return ResponseEntity.ok(activityLogs)
    }

    @GetMapping("/withFriends/{userId}")
    fun getActivityLogsWithFriendsByUserId(
        @PathVariable userId: UUID,
        @RequestParam(required = false) from: LocalDateTime?,
        @RequestParam(required = false) to: LocalDateTime?
    ): ResponseEntity<List<ActivityLogDto>> {
        val today = LocalDate.now()
        val startOfDay = from ?: today.atStartOfDay()
        val endOfDay = to ?: today.atTime(LocalTime.MAX)
        val activityLogs = service.getActivityLogsWithFriendsByUserId(userId, startOfDay, endOfDay)
        return ResponseEntity.ok(activityLogs)
    }
}
