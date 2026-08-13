package io.mailtrap.api.emailcampaigns;

import io.mailtrap.model.request.emailcampaigns.CreateEmailCampaign;
import io.mailtrap.model.request.emailcampaigns.ScheduleEmailCampaignRequest;
import io.mailtrap.model.request.emailcampaigns.UpdateEmailCampaign;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignListResponse;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignResponse;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignStatsResponse;

/**
 * Email Campaigns API. Manage email marketing campaigns and retrieve their performance
 * statistics.
 *
 * <p>These endpoints are token-scoped: the account is resolved from the API token, so the
 * path takes no account id.
 */
public interface EmailCampaigns {

    /**
     * List the account's email campaigns, newest first.
     *
     * @param filter filtering and pagination parameters; {@code null} for the first page with
     *               API defaults
     * @return a page of campaigns and the pagination metadata
     */
    EmailCampaignListResponse getEmailCampaigns(EmailCampaignListFilter filter);

    /**
     * Create a new email campaign in the {@code draft} state.
     *
     * @param request the campaign attributes ({@code name}, {@code domainId},
     *                {@code fromLocalPart} and a template {@code subject} are required)
     * @return the created email campaign
     */
    EmailCampaignResponse createEmailCampaign(CreateEmailCampaign request);

    /**
     * Get a single email campaign by ID.
     *
     * @param emailCampaignId unique email campaign ID
     * @return the email campaign
     */
    EmailCampaignResponse getEmailCampaign(long emailCampaignId);

    /**
     * Update an existing {@code draft} email campaign. Only the provided attributes are
     * changed.
     *
     * @param emailCampaignId unique email campaign ID
     * @param request         the attributes to update
     * @return the updated email campaign
     */
    EmailCampaignResponse updateEmailCampaign(long emailCampaignId, UpdateEmailCampaign request);

    /**
     * Delete an email campaign. Only a campaign in the {@code draft} state can be deleted.
     *
     * @param emailCampaignId unique email campaign ID
     */
    void deleteEmailCampaign(long emailCampaignId);

    /**
     * Start sending a {@code draft} campaign immediately.
     *
     * @param emailCampaignId unique email campaign ID
     * @return the started email campaign
     */
    EmailCampaignResponse startEmailCampaign(long emailCampaignId);

    /**
     * Schedule a {@code draft} campaign to start sending at a future time. The scheduled time
     * is reported back in {@code currentStateMetadata.scheduledAt}.
     *
     * @param emailCampaignId unique email campaign ID
     * @param request         when to start sending the campaign
     * @return the scheduled email campaign
     */
    EmailCampaignResponse scheduleEmailCampaign(long emailCampaignId, ScheduleEmailCampaignRequest request);

    /**
     * Cancel a {@code scheduled} campaign, returning it to the {@code draft} state.
     *
     * @param emailCampaignId unique email campaign ID
     * @return the cancelled email campaign
     */
    EmailCampaignResponse cancelEmailCampaign(long emailCampaignId);

    /**
     * Terminate a campaign that is currently sending ({@code started}, {@code queued} or
     * {@code paused}), aborting the in-flight send.
     *
     * @param emailCampaignId unique email campaign ID
     * @return the terminated email campaign
     */
    EmailCampaignResponse terminateEmailCampaign(long emailCampaignId);

    /**
     * Reset a {@code scheduled} campaign back to the {@code draft} state.
     *
     * @param emailCampaignId unique email campaign ID
     * @return the reset email campaign
     */
    EmailCampaignResponse resetEmailCampaign(long emailCampaignId);

    /**
     * Get aggregated performance statistics for an email campaign. If the campaign has never
     * been started, all counts and rates are returned as {@code 0}.
     *
     * @param emailCampaignId unique email campaign ID
     * @param filter          aggregation window; {@code null} for the whole period since the
     *                        campaign was last started
     * @return aggregated campaign statistics
     */
    EmailCampaignStatsResponse getEmailCampaignStats(long emailCampaignId, EmailCampaignStatsFilter filter);

}
