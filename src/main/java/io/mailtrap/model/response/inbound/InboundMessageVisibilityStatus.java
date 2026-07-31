package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Visibility of a message inside a thread. {@code placeholder} entries omit most
 * fields (e.g. a message the caller cannot fully see).
 */
public enum InboundMessageVisibilityStatus {
    AVAILABLE("available"),
    PLACEHOLDER("placeholder");

    private final String value;

    InboundMessageVisibilityStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static InboundMessageVisibilityStatus fromValue(String value) {
        for (InboundMessageVisibilityStatus status : InboundMessageVisibilityStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown InboundMessageVisibilityStatus value: " + value);
    }
}
