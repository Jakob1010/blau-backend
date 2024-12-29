package com.example.blau.dto

import jooq.tables.pojos.Activities
import jooq.tables.pojos.Activitytemplates
import java.util.UUID

data class ActivityDto(
    val activityId: UUID?,
    val categoryId: UUID,
    val userId: UUID,
    val name: String,
    val description: String?,
    val emoji: String?,
)

data class ActivityTemplateDto(
    val activityId: UUID,
    val categoryId: UUID,
    val name: String,
    val description: String?,
    val emoji: String?,
)

fun Activitytemplates.toDto(): ActivityTemplateDto {
    return ActivityTemplateDto(
        activityId = templateId,
        categoryId = categoryId,
        name = name,
        description = description,
        emoji = emoji
    )
}

fun Activities.toDto(): ActivityDto {
    return ActivityDto(
        activityId = activityId,
        userId = userId,
        categoryId = categoryId,
        name = name,
        description = description,
        emoji = emoji
    )
}
