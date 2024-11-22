package com.example.blau.service

import com.example.blau.dto.CategoryDto
import com.example.blau.dto.toDto
import com.example.blau.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getAllCategories(): List<CategoryDto> =
        categoryRepository.getCategories().map { it.toDto() }
}
