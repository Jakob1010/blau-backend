/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import java.time.OffsetDateTime;
import java.util.UUID;

import jooq.tables.Friendships;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FriendshipsRecord extends UpdatableRecordImpl<FriendshipsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.friendships.friendship_id</code>.
     */
    public FriendshipsRecord setFriendshipId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.friendships.friendship_id</code>.
     */
    public UUID getFriendshipId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.friendships.user1_id</code>.
     */
    public FriendshipsRecord setUser1Id(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.friendships.user1_id</code>.
     */
    public UUID getUser1Id() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.friendships.user2_id</code>.
     */
    public FriendshipsRecord setUser2Id(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.friendships.user2_id</code>.
     */
    public UUID getUser2Id() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.friendships.created_at</code>.
     */
    public FriendshipsRecord setCreatedAt(OffsetDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.friendships.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return (OffsetDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FriendshipsRecord
     */
    public FriendshipsRecord() {
        super(Friendships.FRIENDSHIPS);
    }

    /**
     * Create a detached, initialised FriendshipsRecord
     */
    public FriendshipsRecord(UUID friendshipId, UUID user1Id, UUID user2Id, OffsetDateTime createdAt) {
        super(Friendships.FRIENDSHIPS);

        setFriendshipId(friendshipId);
        setUser1Id(user1Id);
        setUser2Id(user2Id);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FriendshipsRecord
     */
    public FriendshipsRecord(jooq.tables.pojos.Friendships value) {
        super(Friendships.FRIENDSHIPS);

        if (value != null) {
            setFriendshipId(value.getFriendshipId());
            setUser1Id(value.getUser1Id());
            setUser2Id(value.getUser2Id());
            setCreatedAt(value.getCreatedAt());
            resetChangedOnNotNull();
        }
    }
}
