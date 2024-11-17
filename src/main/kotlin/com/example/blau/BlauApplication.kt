package com.example.blau

import com.example.blau.repository.CategoryRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class BlauApplication

fun main(args: Array<String>) {
	val context = runApplication<BlauApplication>(*args)

	// Retrieve the repository bean from the Spring context
	val categoryRepository = context.getBean(CategoryRepository::class.java)

	// Test repository methods
	val categories = categoryRepository.getCategories()
	println(categories) // Replace with actual assertions or output

}
