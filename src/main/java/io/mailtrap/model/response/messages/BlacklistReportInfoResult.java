package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BlacklistReportInfoResult {
    SUCCESS("success"),
    PENDING("pending"),
    ERROR("error");

    private final String value;

    BlacklistReportInfoResult(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static BlacklistReportInfoResult fromValue(Object value) {
        for (BlacklistReportInfoResult level : BlacklistReportInfoResult.values()) {
            if (level.value.equalsIgnoreCase((String) value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
