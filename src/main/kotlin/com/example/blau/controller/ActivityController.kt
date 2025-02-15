package com.example.blau.controller

import com.example.blau.dto.ActivityDto
import com.example.blau.dto.ActivityTemplateDto
import com.example.blau.service.ActivityService
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
@RequestMapping("/api/v1/acty")
class ActivityController(private val service: ActivityService) {

    @GetMapping("/templates")
    fun getTemplates(): ResponseEntity<List<ActivityTemplateDto>> {
        return ResponseEntity.ok(service.getAllActivityTemplates())
    }

    @GetMapping("/{userId}")
    fun getActivitiesByUser(@PathVariable userId: UUID): ResponseEntity<List<ActivityDto>> {
        val activities = service.getAllActivitiesForUser(userId)
        return ResponseEntity.ok(activities)
    }

    @PostMapping
    fun createActivity(@RequestBody activityDto: ActivityDto): ResponseEntity<UUID> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createActivity(activityDto))
    }
}
