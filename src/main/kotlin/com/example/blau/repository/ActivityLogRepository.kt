package com.example.blau.repository

import com.example.blau.dto.ActivityLogDto
import jooq.Tables.ACTIVITYLOGS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class ActivityLogRepository(private val dslContext: DSLContext, context: DSLContext) {

    fun createActivityLog(dto: ActivityLogDto): ActivityLogDto {
        val insertedRecord = dslContext
            .insertInto(ACTIVITYLOGS) // Replace with your actual table name
            .set(ACTIVITYLOGS.ACTIVITY_ID, dto.activityId)
            .set(ACTIVITYLOGS.USER_ID, dto.userId)
            .set(ACTIVITYLOGS.TIMESTAMP, OffsetDateTime.now())
            .set(ACTIVITYLOGS.QUANTITY, dto.quantity?.toBigDecimal())
            .set(ACTIVITYLOGS.LAT, dto.lat?.toBigDecimal())
            .set(ACTIVITYLOGS.LNG, dto.lng?.toBigDecimal())
            .returning() // Return the full record, including any generated values
            .fetchOne()!!

        return insertedRecord.into(ActivityLogDto::class.java)
    }
}
