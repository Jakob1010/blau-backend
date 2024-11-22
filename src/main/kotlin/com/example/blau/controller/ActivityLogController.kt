package com.example.blau.controller

import com.example.blau.dto.ActivityLogDto
import com.example.blau.service.ActivityLogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/log")
class ActivityLogController(private val service: ActivityLogService) {

    @PostMapping
    fun createActivityLog(@RequestBody dto: ActivityLogDto): ResponseEntity<ActivityLogDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createActivityLog(dto))
    }
}
