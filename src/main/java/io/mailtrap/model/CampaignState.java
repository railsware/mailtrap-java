package io.mailtrap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Current state of an email campaign in its lifecycle.
 */
public enum CampaignState {
    DRAFT("draft"),
    SCHEDULED("scheduled"),
    STARTED("started"),
    QUEUED("queued"),
    PAUSED("paused"),
    TERMINATING("terminating"),
    UNDER_REVIEW("under_review"),
    FINISHED("finished"),
    FAILED("failed"),
    FAILED_IMMEDIATELY("failed_immediately");

    private final String value;

    CampaignState(String value) {
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
    public static CampaignState fromValue(String value) {
        for (CampaignState state : CampaignState.values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
