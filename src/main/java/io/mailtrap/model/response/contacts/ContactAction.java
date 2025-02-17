package io.mailtrap.model.response.contacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContactAction {

    // if contact does not exist
    CREATED("created"),

    //if contact exists
    UPDATED("updated"),;

    private final String value;

    ContactAction(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ContactAction fromValue(String value) {
        for (ContactAction action : ContactAction.values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown contact action value: " + value);
    }
}
