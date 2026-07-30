package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * A per-recipient error recorded when campaign sending failed.
 */
@Data
public class CampaignRecipientError {

    private String message;

    @JsonProperty("rcpt_index")
    private Integer rcptIndex;

}
