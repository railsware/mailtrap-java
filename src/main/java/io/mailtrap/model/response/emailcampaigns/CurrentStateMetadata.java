package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Metadata about the most recent campaign state transition. Which fields are present depends
 * on the state.
 */
@Data
public class CurrentStateMetadata {

    private String reason;

    /**
     * Last error message recorded for a failed campaign.
     */
    private String error;

    /**
     * Per-recipient errors recorded when sending failed.
     */
    private List<CampaignRecipientError> errors;

    /**
     * When the campaign is scheduled to send. Present in the {@code scheduled} state.
     */
    @JsonProperty("scheduled_at")
    private OffsetDateTime scheduledAt;

}
