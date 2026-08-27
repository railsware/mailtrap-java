package io.mailtrap.model.request.suppressions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filtering and pagination parameters for listing suppressions. All fields are optional;
 * {@code null} fields are omitted from the query string.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuppressionListFilter {

    /**
     * Filter by exact email address (case-insensitive).
     */
    private String email;

    /**
     * Only suppressions created at or after this ISO 8601 timestamp.
     */
    private String startTime;

    /**
     * Only suppressions created at or before this ISO 8601 timestamp.
     */
    private String endTime;

    /**
     * Cursor: returns suppressions after this UUID.
     */
    private String lastId;
}
