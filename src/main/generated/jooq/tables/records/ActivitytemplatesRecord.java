/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import java.math.BigDecimal;
import java.util.UUID;

import jooq.tables.Activitytemplates;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ActivitytemplatesRecord extends UpdatableRecordImpl<ActivitytemplatesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.activitytemplates.template_id</code>.
     */
    public ActivitytemplatesRecord setTemplateId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.template_id</code>.
     */
    public UUID getTemplateId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.activitytemplates.category_id</code>.
     */
    public ActivitytemplatesRecord setCategoryId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.category_id</code>.
     */
    public UUID getCategoryId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.activitytemplates.name</code>.
     */
    public ActivitytemplatesRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.activitytemplates.description</code>.
     */
    public ActivitytemplatesRecord setDescription(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.activitytemplates.default_quantity</code>.
     */
    public ActivitytemplatesRecord setDefaultQuantity(BigDecimal value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.default_quantity</code>.
     */
    public BigDecimal getDefaultQuantity() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>public.activitytemplates.emoji</code>.
     */
    public ActivitytemplatesRecord setEmoji(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.activitytemplates.emoji</code>.
     */
    public String getEmoji() {
        return (String) get(5);
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
     * Create a detached ActivitytemplatesRecord
     */
    public ActivitytemplatesRecord() {
        super(Activitytemplates.ACTIVITYTEMPLATES);
    }

    /**
     * Create a detached, initialised ActivitytemplatesRecord
     */
    public ActivitytemplatesRecord(UUID templateId, UUID categoryId, String name, String description, BigDecimal defaultQuantity, String emoji) {
        super(Activitytemplates.ACTIVITYTEMPLATES);

        setTemplateId(templateId);
        setCategoryId(categoryId);
        setName(name);
        setDescription(description);
        setDefaultQuantity(defaultQuantity);
        setEmoji(emoji);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ActivitytemplatesRecord
     */
    public ActivitytemplatesRecord(jooq.tables.pojos.Activitytemplates value) {
        super(Activitytemplates.ACTIVITYTEMPLATES);

        if (value != null) {
            setTemplateId(value.getTemplateId());
            setCategoryId(value.getCategoryId());
            setName(value.getName());
            setDescription(value.getDescription());
            setDefaultQuantity(value.getDefaultQuantity());
            setEmoji(value.getEmoji());
            resetChangedOnNotNull();
        }
    }
}
