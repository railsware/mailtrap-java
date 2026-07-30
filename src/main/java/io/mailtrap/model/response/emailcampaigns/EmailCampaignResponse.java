package io.mailtrap.model.response.emailcampaigns;

import lombok.Data;

/**
 * A single email campaign wrapped in the {@code data} envelope, as returned by the create,
 * get, update, and lifecycle action endpoints.
 */
@Data
public class EmailCampaignResponse {

    private EmailCampaign data;

}
