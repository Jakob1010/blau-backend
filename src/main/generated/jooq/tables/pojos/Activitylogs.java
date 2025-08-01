/*
 * This file is generated by jOOQ.
 */
package jooq.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Activitylogs implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UUID logId;
    private final UUID activityId;
    private final UUID userId;
    private final OffsetDateTime timestamp;
    private final BigDecimal quantity;
    private final BigDecimal lat;
    private final BigDecimal lng;

    public Activitylogs(Activitylogs value) {
        this.logId = value.logId;
        this.activityId = value.activityId;
        this.userId = value.userId;
        this.timestamp = value.timestamp;
        this.quantity = value.quantity;
        this.lat = value.lat;
        this.lng = value.lng;
    }

    public Activitylogs(
        UUID logId,
        UUID activityId,
        UUID userId,
        OffsetDateTime timestamp,
        BigDecimal quantity,
        BigDecimal lat,
        BigDecimal lng
    ) {
        this.logId = logId;
        this.activityId = activityId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Getter for <code>public.activitylogs.log_id</code>.
     */
    public UUID getLogId() {
        return this.logId;
    }

    /**
     * Getter for <code>public.activitylogs.activity_id</code>.
     */
    public UUID getActivityId() {
        return this.activityId;
    }

    /**
     * Getter for <code>public.activitylogs.user_id</code>.
     */
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Getter for <code>public.activitylogs.timestamp</code>.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Getter for <code>public.activitylogs.quantity</code>.
     */
    public BigDecimal getQuantity() {
        return this.quantity;
    }

    /**
     * Getter for <code>public.activitylogs.lat</code>.
     */
    public BigDecimal getLat() {
        return this.lat;
    }

    /**
     * Getter for <code>public.activitylogs.lng</code>.
     */
    public BigDecimal getLng() {
        return this.lng;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Activitylogs other = (Activitylogs) obj;
        if (this.logId == null) {
            if (other.logId != null)
                return false;
        }
        else if (!this.logId.equals(other.logId))
            return false;
        if (this.activityId == null) {
            if (other.activityId != null)
                return false;
        }
        else if (!this.activityId.equals(other.activityId))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.timestamp == null) {
            if (other.timestamp != null)
                return false;
        }
        else if (!this.timestamp.equals(other.timestamp))
            return false;
        if (this.quantity == null) {
            if (other.quantity != null)
                return false;
        }
        else if (!this.quantity.equals(other.quantity))
            return false;
        if (this.lat == null) {
            if (other.lat != null)
                return false;
        }
        else if (!this.lat.equals(other.lat))
            return false;
        if (this.lng == null) {
            if (other.lng != null)
                return false;
        }
        else if (!this.lng.equals(other.lng))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.logId == null) ? 0 : this.logId.hashCode());
        result = prime * result + ((this.activityId == null) ? 0 : this.activityId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        result = prime * result + ((this.lat == null) ? 0 : this.lat.hashCode());
        result = prime * result + ((this.lng == null) ? 0 : this.lng.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Activitylogs (");

        sb.append(logId);
        sb.append(", ").append(activityId);
        sb.append(", ").append(userId);
        sb.append(", ").append(timestamp);
        sb.append(", ").append(quantity);
        sb.append(", ").append(lat);
        sb.append(", ").append(lng);

        sb.append(")");
        return sb.toString();
    }
}
