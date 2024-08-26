package io.mailtrap.api.inboxes;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.api_resource.ApiResourceWithValidation;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.inboxes.CreateInboxRequest;
import io.mailtrap.model.request.inboxes.UpdateInboxRequest;
import io.mailtrap.model.response.inboxes.InboxResponse;

import java.util.List;

public class InboxesImpl extends ApiResourceWithValidation implements Inboxes {

    public InboxesImpl(MailtrapConfig config, CustomValidator validator) {
        super(config, validator);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public InboxResponse createInbox(long accountId, long projectId, CreateInboxRequest request) {

        validateRequestBodyAndThrowException(request);

        return httpClient.post(
                String.format(apiHost + "/api/accounts/%s/projects/%s/inboxes", accountId, projectId),
                request,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse getInboxAttributes(long accountId, long inboxId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s", accountId, inboxId),
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse deleteInbox(long accountId, long inboxId) {
        return httpClient.delete(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s", accountId, inboxId),
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse updateInbox(long accountId, long inboxId, UpdateInboxRequest request) {

        validateRequestBodyAndThrowException(request);

        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s", accountId, inboxId),
                request,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse cleanInbox(long accountId, long inboxId) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/clean", accountId, inboxId),
                null,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse markAsRead(long accountId, long inboxId) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/all_read", accountId, inboxId),
                null,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse resetCredentials(long accountId, long inboxId) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/reset_credentials", accountId, inboxId),
                null,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse enableEmailAddress(long accountId, long inboxId) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/toggle_email_username", accountId, inboxId),
                null,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public InboxResponse resetEmailAddresses(long accountId, long inboxId) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/reset_email_username", accountId, inboxId),
                null,
                new RequestData(),
                InboxResponse.class
        );
    }

    @Override
    public List<InboxResponse> getInboxes(long accountId) {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/inboxes", accountId),
                new RequestData(),
                InboxResponse.class);
    }
}
