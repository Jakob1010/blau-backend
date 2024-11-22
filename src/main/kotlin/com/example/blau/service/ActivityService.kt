package com.example.blau.service

import com.example.blau.dto.ActivityDto
import com.example.blau.dto.ActivityTemplateDto
import com.example.blau.dto.toDto
import com.example.blau.repository.ActivityRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ActivityService(
    private val repository: ActivityRepository
) {

    fun getAllActivityTemplates(): List<ActivityTemplateDto> =
        repository.getActivityTemplates().map { it.toDto() }

    fun getAllActivitiesForUser(userId: UUID): List<ActivityDto> =
        repository.getAllActivitiesByUser(userId).map { it.toDto() }

    fun createActivity(activityDto: ActivityDto) =
        repository.createActivity(activityDto)
}
