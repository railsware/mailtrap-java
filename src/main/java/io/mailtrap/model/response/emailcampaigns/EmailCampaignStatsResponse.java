package io.mailtrap.model.response.emailcampaigns;

import lombok.Data;

/**
 * Aggregated campaign statistics wrapped in the {@code data} envelope.
 */
@Data
public class EmailCampaignStatsResponse {

    private EmailCampaignStats data;

}
