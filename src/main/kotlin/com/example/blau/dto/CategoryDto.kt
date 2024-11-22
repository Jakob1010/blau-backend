package com.example.blau.dto

import jooq.tables.pojos.Categories
import java.util.UUID

data class CategoryDto(
    val categoryId: UUID,
    val name: String,
    val description: String?,
    val unit: String?
)

fun Categories.toDto(): CategoryDto {
    return CategoryDto(
        categoryId = categoryId,
        name = name,
        description = description,
        unit = unit
    )
}
