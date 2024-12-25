package com.example.blau.utils

import jooq.tables.pojos.Activities
import jooq.tables.pojos.Categories
import jooq.tables.pojos.Users
import org.jooq.DSLContext
import org.jooq.Table
import org.jooq.impl.DSL
import java.util.UUID

class TestData(private val dsl: DSLContext) {

    fun insertCategory(category: Categories): UUID {
        return dsl.insertInto(
            DSL.table("Categories"),
            DSL.field("name"),
            DSL.field("description"),
            DSL.field("unit")
        )
            .values(category.name, category.description, category.unit)
            .returning(DSL.field("category_id"))
            .fetchOne()!!
            .getValue(DSL.field("category_id"), UUID::class.java)!!
    }

    fun insertUser(users: Users): UUID {
        return dsl.insertInto(
            DSL.table("Users"),
            DSL.field("username"),
        )
            .values(users.username)
            .returning(DSL.field("user_id"))
            .fetchOne()!!
            .getValue(DSL.field("user_id"), UUID::class.java)!!
    }

    fun insertActivity(activities: Activities): UUID {
        return dsl.insertInto(
            DSL.table("Activities"),
            DSL.field("category_id"),
            DSL.field("user_id"),
            DSL.field("name"),
            DSL.field("description"),
        )
            .values(activities.categoryId, activities.userId, activities.name, activities.description)
            .returning(DSL.field("activity_id"))
            .fetchOne()!!
            .getValue(DSL.field("activity_id"), UUID::class.java)!!
    }

    fun truncateTable(table: Table<*>) {
        dsl.truncate(table).cascade().execute()
    }
}
