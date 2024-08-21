package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessLevel {

    OWNER(1000),
    ADMIN(100),
    VIEWER(10),
    INDETERMINATE(1);

    private final int value;

    AccessLevel(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static AccessLevel fromValue(int value) {
        for (AccessLevel level : AccessLevel.values()) {
            if (level.value == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown access_level: " + value);
    }
}