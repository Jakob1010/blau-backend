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
    val templateId: UUID? = null,
)

data class ActivityTemplateDto(
    val templateId: UUID,
    val categoryId: UUID,
    val name: String,
    val description: String?,
    val emoji: String?,
)

fun Activitytemplates.toDto(): ActivityTemplateDto {
    return ActivityTemplateDto(
        templateId = templateId,
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
        emoji = emoji,
        templateId = templateId
    )
}
