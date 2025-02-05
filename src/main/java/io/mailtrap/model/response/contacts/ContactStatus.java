package io.mailtrap.model.response.contacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContactStatus {

    // if email was NOT in suppression list
    SUBSCRIBED("subscribed"),
    UNSUBSCRIBED("unsubscribed");

    private final String value;

    ContactStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ContactStatus fromValue(String value) {
        for (ContactStatus status : ContactStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown contact status value: " + value);
    }
}
