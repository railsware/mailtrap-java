package io.mailtrap.client.api;

import io.mailtrap.api.emailcampaigns.EmailCampaigns;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Email Campaigns functionality.
 *
 * <p>Email campaigns use a token-scoped, non-account-scoped URL family
 * ({@code /api/email_campaigns}), so they are grouped under their own API rather than the
 * account-scoped general API.
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapEmailCampaignsApi {
    private final EmailCampaigns emailCampaigns;
}
