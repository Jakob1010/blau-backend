package com.example.blau.integration

import org.jooq.DSLContext
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest
@ExtendWith(SpringExtension::class)
abstract class IntegrationTest {

    @Autowired
    lateinit var dsl: DSLContext

    companion object {
        private val postgresContainer = PostgreSQLContainer("postgres:15.1")
            .withDatabaseName("postgres")
            .withExposedPorts(5432)
            .withUsername("user")
            .withPassword("pass")

        init {
            postgresContainer.start()

            // Apply the schema on initialization
            val schemaPath = "db/init.sql"
            applySchema(schemaPath)
        }

        @JvmStatic
        @DynamicPropertySource
        fun dataSourceProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgresContainer.getJdbcUrl() }
            registry.add("spring.datasource.username") { postgresContainer.username }
            registry.add("spring.datasource.password") { postgresContainer.password }
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            postgresContainer.stop()
        }

        // TODO: Use a library for migrations
        private fun applySchema(schemaPath: String) {
            val schema = IntegrationTest::class.java.classLoader.getResource(schemaPath)?.readText()
                ?: throw IllegalArgumentException("Schema file not found at $schemaPath")
            postgresContainer.createConnection("").use { connection ->
                connection.createStatement().use { statement ->
                    statement.execute(schema)
                }
            }
        }
    }
}
