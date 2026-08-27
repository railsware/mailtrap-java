package io.mailtrap.model.request.trackingoptouts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filtering and pagination parameters for listing tracking opt-outs. All fields are optional;
 * {@code null} fields are omitted from the query string.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingOptOutListFilter {

    /**
     * Filter by exact email address (case-insensitive).
     */
    private String email;

    /**
     * Only opt-outs created at or after this ISO 8601 timestamp.
     */
    private String startTime;

    /**
     * Only opt-outs created at or before this ISO 8601 timestamp.
     */
    private String endTime;

    /**
     * Cursor from the previous response's {@code last_id}.
     */
    private String lastId;
}
