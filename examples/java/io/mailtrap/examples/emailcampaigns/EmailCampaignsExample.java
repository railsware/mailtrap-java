package io.mailtrap.examples.emailcampaigns;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.DeliveryMode;
import io.mailtrap.model.request.emailcampaigns.CreateEmailCampaign;
import io.mailtrap.model.request.emailcampaigns.ScheduleEmailCampaignRequest;
import io.mailtrap.model.request.emailcampaigns.TemplateAttributes;
import io.mailtrap.model.request.emailcampaigns.UpdateEmailCampaign;
import io.mailtrap.model.response.emailcampaigns.DeliveryOptions;
import io.mailtrap.model.response.emailcampaigns.ReplyTo;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class EmailCampaignsExample {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    // ID of a verified sending domain on the account, as returned by the Sending Domains endpoints.
    private static final long DOMAIN_ID = 4321L;
    private static final long CONTACT_LIST_ID = 55L;

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
            .token(TOKEN)
            .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);

        // The campaign endpoints are token-scoped: the account is resolved from the API token.
        final var campaigns = client.emailCampaignsApi().emailCampaigns();

        // List campaigns (newest first). `search` filters by name; `token` is the page number.
        final var page = campaigns.getEmailCampaigns(50, "Spring", 1);
        System.out.println(page);

        // Create a campaign — it starts in the `draft` state. The request body is flat.
        final var created = campaigns.createEmailCampaign(
            CreateEmailCampaign.builder()
                .name("Spring Sale")
                .domainId(DOMAIN_ID)
                .fromDisplayName("Acme Marketing")
                .fromLocalPart("news")
                .replyTo(ReplyTo.builder()
                    .displayName("Acme Support")
                    .localPart("support")
                    .domain("acme.com")
                    .build())
                .templateAttributes(TemplateAttributes.builder()
                    .subject("Spring is here — 30% off")
                    .build())
                .contactListIds(List.of(CONTACT_LIST_ID))
                .build());
        System.out.println(created.getData());

        final var campaignId = created.getData().getId();

        // Retrieve a single campaign.
        final var fetched = campaigns.getEmailCampaign(campaignId);
        System.out.println(fetched.getData());

        // Update is a PATCH — only the provided fields change. The template is edited in place;
        // `bodyHtml` is the design and must contain an unsubscribe link.
        final var updated = campaigns.updateEmailCampaign(campaignId,
            UpdateEmailCampaign.builder()
                .name("Spring Sale (updated)")
                .templateAttributes(TemplateAttributes.builder()
                    .subject("New subject")
                    .bodyHtml("<html><body><h1>Hi {{first_name}}!</h1>"
                        + "<p><a href=\"__unsubscribe_url__\">Unsubscribe</a></p></body></html>")
                    .mergeTags(List.of("first_name"))
                    .build())
                .deliveryMode(DeliveryMode.GRADUAL)
                .deliveryOptions(DeliveryOptions.builder().emailsPerHour(1000).build())
                .build());
        System.out.println(updated.getData());

        // Schedule the draft to send later — the time must be in the future, at most 1 month
        // ahead; it comes back in currentStateMetadata.scheduledAt.
        final var scheduled = campaigns.scheduleEmailCampaign(campaignId,
            new ScheduleEmailCampaignRequest(OffsetDateTime.now(ZoneOffset.UTC).plusDays(1)));
        System.out.println(scheduled.getData().getCurrentStateMetadata().getScheduledAt());

        // Cancel the scheduled send — the campaign returns to `draft`.
        final var cancelled = campaigns.cancelEmailCampaign(campaignId);
        System.out.println(cancelled.getData().getCurrentState());

        // Or start sending immediately.
        final var started = campaigns.startEmailCampaign(campaignId);
        System.out.println(started.getData().getCurrentState());

        // Aggregated performance statistics; narrow the window with start/end dates (YYYY-MM-DD).
        final var today = LocalDate.now(ZoneOffset.UTC);
        final var stats = campaigns.getEmailCampaignStats(campaignId, today.minusDays(30).toString(), today.toString());
        System.out.println(stats.getData());

        // Delete returns 204 No Content.
        campaigns.deleteEmailCampaign(campaignId);
    }
}
