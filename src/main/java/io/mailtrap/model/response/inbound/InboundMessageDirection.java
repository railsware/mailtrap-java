package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Direction of a message inside a thread: {@code inbound} (received) or
 * {@code outbound} (sent as a reply, reply-all, or forward).
 */
public enum InboundMessageDirection {
    INBOUND("inbound"),
    OUTBOUND("outbound");

    private final String value;

    InboundMessageDirection(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static InboundMessageDirection fromValue(String value) {
        for (InboundMessageDirection direction : InboundMessageDirection.values()) {
            if (direction.value.equalsIgnoreCase(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unknown InboundMessageDirection value: " + value);
    }
}
