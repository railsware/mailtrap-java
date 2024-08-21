package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AccessLevel {

    OWNER(1000, "owner"),
    ADMIN(100, "admin"),
    VIEWER(10, "viewer"),
    INDETERMINATE(1, "indeterminate");

    @Getter
    private final int intValue;
    private final String stringValue;

    AccessLevel(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    @JsonValue
    public String getStringValue() {
        return stringValue;
    }

    @JsonCreator
    public static AccessLevel fromValue(Object value) {
        if (value instanceof Integer) {
            for (AccessLevel level : AccessLevel.values()) {
                if (level.intValue == (Integer) value) {
                    return level;
                }
            }
        } else if (value instanceof String) {
            for (AccessLevel level : AccessLevel.values()) {
                if (level.stringValue.equalsIgnoreCase((String) value)) {
                    return level;
                }
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}