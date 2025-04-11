package com.example.blau.repository

import com.example.blau.dto.ActivityLogDto
import jooq.Tables.ACTIVITYLOGS
import jooq.Tables.FRIENDSHIPS
import jooq.Tables.USERS
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID

@Repository
class ActivityLogRepository(private val dslContext: DSLContext) {

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

    fun getActivityLogsByUserId(userId: UUID, activityIds: List<UUID>? = null): List<ActivityLogDto> =
        dslContext
            .selectFrom(ACTIVITYLOGS)
            .where(ACTIVITYLOGS.USER_ID.eq(userId))
            .apply {
                activityIds?.let {
                    and(ACTIVITYLOGS.ACTIVITY_ID.`in`(activityIds))
                }
            }
            .fetchInto(ActivityLogDto::class.java)

    fun getActivityLogsByUserIdWithFriends(userId: UUID, from: LocalDateTime, to: LocalDateTime): List<ActivityLogDto> {
        // Step 1: get friend IDs
        val friendIds = dslContext
            .select(USERS.USER_ID)
            .from(FRIENDSHIPS)
            .join(USERS)
            .on(
                USERS.USER_ID.eq(FRIENDSHIPS.USER1_ID)
                    .or(USERS.USER_ID.eq(FRIENDSHIPS.USER2_ID))
            )
            .where(
                (FRIENDSHIPS.USER1_ID.eq(userId).or(FRIENDSHIPS.USER2_ID.eq(userId)))
                    .and(USERS.USER_ID.ne(userId))
            )
            .fetch(USERS.USER_ID)
            .plus(userId) // Include the user themself

        // Step 2: fetch activity logs for user + friends in time range
        val fieldFrom = DSL.value(from.atOffset(ZoneOffset.UTC))
        val fieldTo = DSL.value(to.atOffset(ZoneOffset.UTC))

        return dslContext
            .selectFrom(ACTIVITYLOGS)
            .where(ACTIVITYLOGS.USER_ID.`in`(friendIds))
            .and(ACTIVITYLOGS.TIMESTAMP.between(fieldFrom, fieldTo))
            .fetchInto(ActivityLogDto::class.java)
    }



}
