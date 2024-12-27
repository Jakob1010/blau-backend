package com.example.blau.integration

import com.example.blau.dto.toDto
import com.example.blau.repository.CategoryRepository
import jooq.tables.pojos.Categories
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@Disabled
class CategoryRepositoryIntegrationTest : IntegrationTest() {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    private val testCategory1 = Categories(
        null,
        "Sport",
        "Physical Activity",
        "minutes"
    )

    @Test
    fun `get all Categories on empty table`() {
        // when
        val result = categoryRepository.getCategories()

        // then
        assertEquals(0, result.size)
    }

    @Test
    fun `get all Categories after insert`() {
        // given
        testDataInserter.insertCategory(testCategory1)

        // when
        val result = categoryRepository.getCategories().map { it.toDto() }

        // then
        assertEquals(1, result.size)
        assertEquals(result.first().name, testCategory1.name)
        assertEquals(result.first().description, testCategory1.description)
        assertEquals(result.first().unit, testCategory1.unit)
    }
}
