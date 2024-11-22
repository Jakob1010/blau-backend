package com.example.blau.dto

import jooq.tables.records.ActivitylogsRecord
import java.time.LocalDateTime
import java.util.UUID

data class ActivityLogDto(
    val logId: UUID?,
    val activityId: UUID,
    val userId: UUID,
    val timestamp: LocalDateTime?,
    val quantity: Double?,
    val lat: Double?,
    val lng: Double?
)

fun ActivitylogsRecord.toDto(): ActivityLogDto {
    return ActivityLogDto(
        logId = logId,
        activityId = activityId,
        userId = userId,
        timestamp = timestamp.toLocalDateTime(),
        quantity = quantity.toDouble(),
        lat = lat.toDouble(),
        lng = lng.toDouble()
    )
}
