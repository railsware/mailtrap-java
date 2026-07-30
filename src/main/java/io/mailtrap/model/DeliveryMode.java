package io.mailtrap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * How an email campaign is delivered. {@code RAPID} sends as fast as possible; {@code GRADUAL}
 * throttles sending to {@code delivery_options.emails_per_hour}.
 */
public enum DeliveryMode {
    RAPID("rapid"),
    GRADUAL("gradual");

    private final String value;

    DeliveryMode(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static DeliveryMode fromValue(String value) {
        for (DeliveryMode mode : DeliveryMode.values()) {
            if (mode.value.equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
