package io.mailtrap.model.request.apitokens;

import com.fasterxml.jackson.annotation.JsonValue;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * API token expiration sent as the {@code expires_at} request field.
 * Use {@link #at(OffsetDateTime)} for a concrete expiration or {@link #never()} for a token
 * that never expires (serialized as JSON {@code null}).
 */
public final class TokenExpiration {

    private final String value;

    private TokenExpiration(final String value) {
        this.value = value;
    }

    /**
     * Token expires at the given moment. Past or more-than-5-years-ahead values are rejected
     * by the API with a 422 error.
     *
     * @param value expiration date-time
     * @return expiration serialized as an ISO 8601 date-time string
     */
    public static TokenExpiration at(final OffsetDateTime value) {
        return new TokenExpiration(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value));
    }

    /**
     * Token never expires.
     *
     * @return expiration serialized as JSON {@code null}
     */
    public static TokenExpiration never() {
        return new TokenExpiration(null);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
