package com.example.blau.repository

import com.example.blau.dto.FriendshipRequestDto
import jooq.Tables.FRIENDSHIPREQUESTS
import jooq.Tables.FRIENDSHIPS
import jooq.Tables.USERS
import jooq.tables.pojos.Friendshiprequests
import jooq.tables.pojos.Users
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class FriendshipRepository(
    private val dslContext: DSLContext
) {

    fun updateFriendshipRequestStatus(requestId: UUID, newStatus: String): Friendshiprequests {
        return dslContext
            .update(FRIENDSHIPREQUESTS)
            .set(FRIENDSHIPREQUESTS.STATUS, newStatus)
            .where(FRIENDSHIPREQUESTS.REQUEST_ID.eq(requestId))
            .returning()
            .fetchOneInto(Friendshiprequests::class.java)!!
    }

    fun createFriendshipRequest(dto: FriendshipRequestDto): Friendshiprequests {
        return dslContext
            .insertInto(FRIENDSHIPREQUESTS)
            .set(FRIENDSHIPREQUESTS.SENDER_ID, dto.senderId)
            .set(FRIENDSHIPREQUESTS.RECEIVER_ID, dto.receiverId)
            .set(FRIENDSHIPREQUESTS.STATUS, "pending")
            .returning()
            .fetchOneInto(Friendshiprequests::class.java)!!
    }

    fun acceptFriendship(requestId: UUID): Friendshiprequests {
        return dslContext.transactionResult { config ->
            val ctx = DSL.using(config)

            val friendshipRequest = ctx
                .selectFrom(FRIENDSHIPREQUESTS)
                .where(FRIENDSHIPREQUESTS.REQUEST_ID.eq(requestId))
                .fetchOneInto(Friendshiprequests::class.java)
                ?: throw IllegalStateException("Friendship request not found")

            // Mark request as accepted
            val updatedRequest = ctx.update(FRIENDSHIPREQUESTS)
                .set(FRIENDSHIPREQUESTS.STATUS, "accepted")
                .where(FRIENDSHIPREQUESTS.REQUEST_ID.eq(requestId))
                .returning()
                .fetchOneInto(Friendshiprequests::class.java)!!

            // Insert into Friendships table
            val user1 = minOf(friendshipRequest.senderId, friendshipRequest.receiverId)
            val user2 = maxOf(friendshipRequest.senderId, friendshipRequest.receiverId)

            ctx.insertInto(FRIENDSHIPS)
                .set(FRIENDSHIPS.USER1_ID, user1)
                .set(FRIENDSHIPS.USER2_ID, user2)
                .execute()

            return@transactionResult updatedRequest
        }
    }

    fun getAllFriends(userId: UUID): List<Users> {
        return dslContext
            .select(USERS.USER_ID, USERS.USERNAME, USERS.EMAIL)
            .from(FRIENDSHIPS)
            .join(USERS)
            .on(USERS.USER_ID.eq(FRIENDSHIPS.USER1_ID).or(USERS.USER_ID.eq(FRIENDSHIPS.USER2_ID)))
            .where((FRIENDSHIPS.USER1_ID.eq(userId).or(FRIENDSHIPS.USER2_ID.eq(userId))))
            .fetchInto(Users::class.java)
    }

    fun getFriendshipRequests(userId: UUID): List<Friendshiprequests> {
        return dslContext
            .selectFrom(FRIENDSHIPREQUESTS)
            .where(FRIENDSHIPREQUESTS.RECEIVER_ID.eq(userId))
            .fetchInto(Friendshiprequests::class.java)
    }

    fun getFriendshipRequest(senderId: UUID, receiverId: UUID): Friendshiprequests? {
        return dslContext
            .selectFrom(FRIENDSHIPREQUESTS)
            .where(
                FRIENDSHIPREQUESTS.SENDER_ID.eq(senderId)
                    .and(FRIENDSHIPREQUESTS.RECEIVER_ID.eq(receiverId))
            )
            .fetchOneInto(Friendshiprequests::class.java)
    }
}
