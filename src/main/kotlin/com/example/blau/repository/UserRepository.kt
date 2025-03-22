package com.example.blau.repository

import com.example.blau.dto.UserDto
import jooq.Tables.FRIENDSHIPREQUESTS
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

    fun searchUsersByUsername(search: String?, userId: UUID): List<Users> {
        val pattern = if (search.isNullOrEmpty()) "%" else "%$search%"

        return dsl
            .select(USERS.asterisk())
            .from(USERS)
            .leftJoin(FRIENDSHIPREQUESTS)
            .on(
                USERS.USER_ID.eq(FRIENDSHIPREQUESTS.RECEIVER_ID)
                    .and(FRIENDSHIPREQUESTS.SENDER_ID.eq(userId))
                    .or(
                        USERS.USER_ID.eq(FRIENDSHIPREQUESTS.SENDER_ID)
                            .and(FRIENDSHIPREQUESTS.RECEIVER_ID.eq(userId))
                    )
            )
            .where(
                USERS.USERNAME.likeIgnoreCase(pattern)
                    .and(USERS.USER_ID.notEqual(userId))
                    .and(FRIENDSHIPREQUESTS.REQUEST_ID.isNull)
            )
            .fetchInto(Users::class.java)
    }
}
