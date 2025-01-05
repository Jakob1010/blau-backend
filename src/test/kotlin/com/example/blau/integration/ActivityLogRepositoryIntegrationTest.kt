package com.example.blau.integration

import com.example.blau.dto.ActivityLogDto
import com.example.blau.repository.ActivityLogRepository
import jooq.tables.pojos.Activities
import jooq.tables.pojos.Categories
import jooq.tables.pojos.Users
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@Disabled
class ActivityLogRepositoryIntegrationTest : IntegrationTest() {

    @Autowired
    lateinit var activityLogRepository: ActivityLogRepository

    private val testCategory = Categories(
        null,
        "Sport",
        "Physical Activity",
        "minutes"
    )
    private val testUser = Users(
        null,
        "Leo",
        null,
        null,
        null,
        null
    )

    @BeforeEach
    fun setup() {
    }

    @Test
    fun `insert activity log`() {
        // given
        val categoryId = testDataInserter.insertCategory(testCategory)
        val userId = testDataInserter.insertUser(testUser)
        val activityId = testDataInserter.insertActivity(
            Activities(
                null,
                categoryId,
                userId,
                "Running",
                "super fun activity",
                null
            )
        )
        val testActivityLog = ActivityLogDto(
            activityId = activityId,
            userId = userId,
            quantity = 5.0,
            lat = 48.2082,
            lng = 16.3738,
            timestamp = null,
            logId = null
        )

        // when
        val testActivityLogInserted = activityLogRepository.createActivityLog(testActivityLog)

        // then
        assertNotNull(testActivityLogInserted.logId)
        assertEquals(testActivityLog.activityId, testActivityLogInserted.activityId)
        assertEquals(testActivityLog.userId, testActivityLogInserted.userId)
        assertEquals(testActivityLog.quantity, testActivityLogInserted.quantity)
        assertEquals(testActivityLog.lat, testActivityLogInserted.lat)
        assertEquals(testActivityLog.lng, testActivityLogInserted.lng)
        assertNotNull(testActivityLogInserted.timestamp)
    }

    @Test
    fun `get all activity logs for a user`() {
        // given
        val categoryId = testDataInserter.insertCategory(testCategory)
        val userId = testDataInserter.insertUser(testUser)
        val activityId = testDataInserter.insertActivity(
            Activities(
                null,
                categoryId,
                userId,
                "Running",
                "super fun activity",
                null
            )
        )
        val testActivityLog = ActivityLogDto(
            activityId = activityId,
            userId = userId,
            quantity = 5.0,
            lat = 48.2082,
            lng = 16.3738,
            timestamp = null,
            logId = null
        )

        // when
        val inserted1 = activityLogRepository.createActivityLog(testActivityLog)
        val inserted2 = activityLogRepository.createActivityLog(testActivityLog)
        val userLogs = activityLogRepository.getActivityLogsByUserId(userId)

        // then
        assertEquals(userLogs.size, 2)
        assertTrue(inserted1 in userLogs)
        assertTrue(inserted2 in userLogs)
    }
}
