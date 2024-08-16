package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SpecifierType {
    USER("User"),
    INVITE("Invite"),
    API_TOKEN("ApiToken");

    private final String value;

    SpecifierType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SpecifierType fromValue(String value) {
        for (SpecifierType type : SpecifierType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid specifier type: " + value);
    }
}
