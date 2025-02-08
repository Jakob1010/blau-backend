package com.example.blau.repository

import com.example.blau.dto.ActivityDto
import jooq.Tables.ACTIVITIES
import jooq.Tables.ACTIVITYTEMPLATES
import jooq.tables.pojos.Activities
import jooq.tables.pojos.Activitytemplates
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ActivityRepository(private val dslContext: DSLContext) {

    fun createActivity(dto: ActivityDto): UUID {
        return dslContext
            .insertInto(ACTIVITIES)
            .set(ACTIVITIES.CATEGORY_ID, dto.categoryId)
            .set(ACTIVITIES.USER_ID, dto.userId)
            .set(ACTIVITIES.NAME, dto.name)
            .set(ACTIVITIES.DESCRIPTION, dto.description)
            .set(ACTIVITIES.EMOJI, dto.emoji)
            .set(ACTIVITIES.TEMPLATE_ID, dto.templateId)
            .returning(ACTIVITIES.ACTIVITY_ID)
            .fetchOne()!!
            .activityId!!
    }

    fun getAllActivitiesByUser(userId: UUID): List<Activities> {
        return dslContext
            .selectFrom(ACTIVITIES)
            .fetchInto(Activities::class.java)
    }

    fun getActivityTemplates(): List<Activitytemplates> {
        return dslContext
            .selectFrom(ACTIVITYTEMPLATES)
            .fetchInto(Activitytemplates::class.java)
    }
}
