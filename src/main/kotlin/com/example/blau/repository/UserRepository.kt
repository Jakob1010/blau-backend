package com.example.blau.repository

import com.example.blau.dto.UserDto
import jooq.tables.Users.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val dsl: DSLContext) {

    fun findByUsername(username: String): UserDto? {
        return dsl.select(USERS.USERNAME, USERS.EMAIL, USERS.PASSWORD, USERS.ROLE)
            .from(USERS)
            .where(USERS.USERNAME.eq(username))
            .fetchOneInto(UserDto::class.java)
    }

    fun save(user: UserDto): Int {
        return dsl.insertInto(USERS)
            .set(USERS.USERNAME, user.username)
            .set(USERS.EMAIL, user.email)
            .set(USERS.PASSWORD, user.password)
            .set(USERS.ROLE, user.role)
            .execute()
    }
}