/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import java.util.UUID;

import jooq.tables.Activities;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ActivitiesRecord extends UpdatableRecordImpl<ActivitiesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.activities.activity_id</code>.
     */
    public ActivitiesRecord setActivityId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.activity_id</code>.
     */
    public UUID getActivityId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.activities.category_id</code>.
     */
    public ActivitiesRecord setCategoryId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.category_id</code>.
     */
    public UUID getCategoryId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.activities.user_id</code>.
     */
    public ActivitiesRecord setUserId(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.activities.name</code>.
     */
    public ActivitiesRecord setName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.activities.description</code>.
     */
    public ActivitiesRecord setDescription(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.description</code>.
     */
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.activities.emoji</code>.
     */
    public ActivitiesRecord setEmoji(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.emoji</code>.
     */
    public String getEmoji() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.activities.template_id</code>.
     */
    public ActivitiesRecord setTemplateId(UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.activities.template_id</code>.
     */
    public UUID getTemplateId() {
        return (UUID) get(6);
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
     * Create a detached ActivitiesRecord
     */
    public ActivitiesRecord() {
        super(Activities.ACTIVITIES);
    }

    /**
     * Create a detached, initialised ActivitiesRecord
     */
    public ActivitiesRecord(UUID activityId, UUID categoryId, UUID userId, String name, String description, String emoji, UUID templateId) {
        super(Activities.ACTIVITIES);

        setActivityId(activityId);
        setCategoryId(categoryId);
        setUserId(userId);
        setName(name);
        setDescription(description);
        setEmoji(emoji);
        setTemplateId(templateId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ActivitiesRecord
     */
    public ActivitiesRecord(jooq.tables.pojos.Activities value) {
        super(Activities.ACTIVITIES);

        if (value != null) {
            setActivityId(value.getActivityId());
            setCategoryId(value.getCategoryId());
            setUserId(value.getUserId());
            setName(value.getName());
            setDescription(value.getDescription());
            setEmoji(value.getEmoji());
            setTemplateId(value.getTemplateId());
            resetChangedOnNotNull();
        }
    }
}
