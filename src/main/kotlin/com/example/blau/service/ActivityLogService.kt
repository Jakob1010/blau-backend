package com.example.blau.service

import com.example.blau.dto.ActivityLogDto
import com.example.blau.repository.ActivityLogRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ActivityLogService(private val repository: ActivityLogRepository) {

    fun createActivityLog(activityLogDto: ActivityLogDto): ActivityLogDto =
        repository.createActivityLog(activityLogDto)

    fun getActivityLogsByUserId(userId: UUID, activityIds: List<UUID>?): List<ActivityLogDto> =
        repository.getActivityLogsByUserId(userId, activityIds)
}
