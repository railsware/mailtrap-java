package io.mailtrap.api.emailcampaigns;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.AbstractModel;
import io.mailtrap.model.request.emailcampaigns.CreateEmailCampaign;
import io.mailtrap.model.request.emailcampaigns.ScheduleEmailCampaignRequest;
import io.mailtrap.model.request.emailcampaigns.UpdateEmailCampaign;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignListResponse;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignResponse;
import io.mailtrap.model.response.emailcampaigns.EmailCampaignStatsResponse;

import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class EmailCampaignsImpl extends ApiResource implements EmailCampaigns {

    private static final String BASE_PATH = "/api/email_campaigns";

    public EmailCampaignsImpl(final MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public EmailCampaignListResponse getEmailCampaigns(final EmailCampaignListFilter filter) {
        final var queryParams = RequestData.buildQueryParams(
            entry("per_page", Optional.ofNullable(filter).map(EmailCampaignListFilter::getPerPage)),
            entry("search", Optional.ofNullable(filter).map(EmailCampaignListFilter::getSearch)),
            entry("token", Optional.ofNullable(filter).map(EmailCampaignListFilter::getToken))
        );

        return httpClient.get(
            apiHost + BASE_PATH,
            new RequestData(queryParams),
            EmailCampaignListResponse.class
        );
    }

    @Override
    public EmailCampaignResponse createEmailCampaign(final CreateEmailCampaign request) {
        return httpClient.post(
            apiHost + BASE_PATH,
            request,
            new RequestData(),
            EmailCampaignResponse.class
        );
    }

    @Override
    public EmailCampaignResponse getEmailCampaign(final long emailCampaignId) {
        return httpClient.get(
            String.format(apiHost + BASE_PATH + "/%d", emailCampaignId),
            new RequestData(),
            EmailCampaignResponse.class
        );
    }

    @Override
    public EmailCampaignResponse updateEmailCampaign(final long emailCampaignId, final UpdateEmailCampaign request) {
        return httpClient.patch(
            String.format(apiHost + BASE_PATH + "/%d", emailCampaignId),
            request,
            new RequestData(),
            EmailCampaignResponse.class
        );
    }

    @Override
    public void deleteEmailCampaign(final long emailCampaignId) {
        httpClient.delete(
            String.format(apiHost + BASE_PATH + "/%d", emailCampaignId),
            new RequestData(),
            Void.class
        );
    }

    @Override
    public EmailCampaignResponse startEmailCampaign(final long emailCampaignId) {
        return performLifecycleAction(emailCampaignId, "start");
    }

    @Override
    public EmailCampaignResponse scheduleEmailCampaign(final long emailCampaignId, final ScheduleEmailCampaignRequest request) {
        return httpClient.post(
            String.format(apiHost + BASE_PATH + "/%d/schedule", emailCampaignId),
            request,
            new RequestData(),
            EmailCampaignResponse.class
        );
    }

    @Override
    public EmailCampaignResponse cancelEmailCampaign(final long emailCampaignId) {
        return performLifecycleAction(emailCampaignId, "cancel");
    }

    @Override
    public EmailCampaignResponse terminateEmailCampaign(final long emailCampaignId) {
        return performLifecycleAction(emailCampaignId, "terminate");
    }

    @Override
    public EmailCampaignResponse resetEmailCampaign(final long emailCampaignId) {
        return performLifecycleAction(emailCampaignId, "reset");
    }

    @Override
    public EmailCampaignStatsResponse getEmailCampaignStats(final long emailCampaignId, final EmailCampaignStatsFilter filter) {
        final var queryParams = RequestData.buildQueryParams(
            entry("start_date", Optional.ofNullable(filter).map(EmailCampaignStatsFilter::getStartDate)),
            entry("end_date", Optional.ofNullable(filter).map(EmailCampaignStatsFilter::getEndDate))
        );

        return httpClient.get(
            String.format(apiHost + BASE_PATH + "/%d/stats", emailCampaignId),
            new RequestData(queryParams),
            EmailCampaignStatsResponse.class
        );
    }

    private EmailCampaignResponse performLifecycleAction(final long emailCampaignId, final String action) {
        return httpClient.post(
            String.format(apiHost + BASE_PATH + "/%d/%s", emailCampaignId, action),
            (AbstractModel) null,
            new RequestData(),
            EmailCampaignResponse.class
        );
    }
}
