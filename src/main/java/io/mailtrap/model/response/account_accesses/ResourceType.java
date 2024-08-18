package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceType {
    ACCOUNT("account"),
    BILLING("billing"),
    PROJECT("project"),
    INBOX("inbox"),
    MAILSEND_DOMAIN("mailsend_domain"),
    EMAIL_CAMPAIGN_PERMISSION_SCOPE("email_campaign_permission_scope");

    private final String value;

    ResourceType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ResourceType fromValue(String value) {
        for (ResourceType type : ResourceType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
