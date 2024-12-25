package com.example.blau.integration

import com.example.blau.utils.TestData
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
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

    lateinit var testDataInserter: TestData

    @BeforeEach
    fun setUp() {
        testDataInserter = TestData(dsl)
    }

    @AfterEach
    fun cleanUpTestData() {
        testDataInserter.truncateTable(DSL.table("Categories"))
        testDataInserter.truncateTable(DSL.table("Users"))
        testDataInserter.truncateTable(DSL.table("Activities"))
    }

    companion object {
        private val postgresContainer = PostgreSQLContainer("postgres:15.1")
            .withDatabaseName("postgres")
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
