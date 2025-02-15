package com.example.blau.repository

import com.example.blau.dto.UserDto
import jooq.tables.Users.USERS
import jooq.tables.pojos.Users
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
class UserRepository(private val dsl: DSLContext) {

    fun findByUsername(username: String): UserDto? {
        return dsl.selectFrom(USERS)
            .where(USERS.USERNAME.eq(username))
            .fetchOneInto(UserDto::class.java)
    }

    fun save(user: UserDto): Int {
        return dsl.insertInto(USERS)
            .set(USERS.USERNAME, user.username)
            .set(USERS.EMAIL, user.email)
            .set(USERS.PASSWORD, user.password)
            .set(USERS.ROLE, user.role!!.name)
            .execute()
    }

    fun findByToken(token: String): UserDto? {
        return dsl.selectFrom(USERS)
            .where(USERS.TOKEN.eq(token))
            .and(USERS.TOKEN_EXPIRY.greaterThan(LocalDateTime.now()))
            .fetchOneInto(UserDto::class.java)
    }

    fun updateToken(userId: UUID, token: String, expiry: LocalDateTime) {
        dsl.update(USERS)
            .set(USERS.TOKEN, token)
            .set(USERS.TOKEN_EXPIRY, expiry)
            .where(USERS.USER_ID.eq(userId))
            .execute()
    }

    fun getAllUsers(): List<Users> {
        return dsl
            .selectFrom(USERS)
            .fetchInto(Users::class.java)
    }

    fun searchUsersByUsername(search: String): List<Users> {
        return dsl
            .selectFrom(USERS)
            .where(USERS.USERNAME.likeIgnoreCase("%$search%"))
            .fetchInto(Users::class.java)
    }
}
