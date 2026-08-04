package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.MailtrapValidator;
import io.mailtrap.api.apiresource.ApiResourceWithValidation;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.inbound.InboundForwardRequest;
import io.mailtrap.model.request.inbound.InboundReplyRequest;
import io.mailtrap.model.response.inbound.InboundMessage;
import io.mailtrap.model.response.inbound.InboundMessagesListResponse;
import io.mailtrap.model.response.inbound.InboundSendResult;

import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class InboundMessagesImpl extends ApiResourceWithValidation implements InboundMessages {

    public InboundMessagesImpl(final MailtrapConfig config, final MailtrapValidator mailtrapValidator) {
        super(config, mailtrapValidator);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public InboundMessagesListResponse list(final long inboxId, final String lastId) {
        final var queryParams = RequestData.buildQueryParams(
            entry("last_id", Optional.ofNullable(lastId))
        );
        return httpClient.get(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages", inboxId),
            new RequestData(queryParams),
            InboundMessagesListResponse.class
        );
    }

    @Override
    public InboundMessage get(final long inboxId, final String messageId) {
        return httpClient.get(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages/%s", inboxId, messageId),
            new RequestData(),
            InboundMessage.class
        );
    }

    @Override
    public void delete(final long inboxId, final String messageId) {
        httpClient.delete(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages/%s", inboxId, messageId),
            new RequestData(),
            Void.class
        );
    }

    @Override
    public InboundSendResult reply(final long inboxId, final String messageId, final InboundReplyRequest request) {
        return httpClient.post(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages/%s/reply", inboxId, messageId),
            request,
            new RequestData(),
            InboundSendResult.class
        );
    }

    @Override
    public InboundSendResult replyAll(final long inboxId, final String messageId, final InboundReplyRequest request) {
        return httpClient.post(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages/%s/reply_all", inboxId, messageId),
            request,
            new RequestData(),
            InboundSendResult.class
        );
    }

    @Override
    public InboundSendResult forward(final long inboxId, final String messageId, final InboundForwardRequest request) {
        validateRequestBodyAndThrowException(request);
        return httpClient.post(
            String.format(apiHost + "/api/inbound/inboxes/%d/messages/%s/forward", inboxId, messageId),
            request,
            new RequestData(),
            InboundSendResult.class
        );
    }
}
