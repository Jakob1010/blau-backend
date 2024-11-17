package com.example.blau.repository

import jooq.Tables.CATEGORIES
import jooq.tables.pojos.Categories
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CategoryRepository(private val dslContext: DSLContext) {

    fun getCategories(): List<Categories> {
        return dslContext
            .selectFrom(CATEGORIES)
            .fetchInto(Categories::class.java)
    }
}
