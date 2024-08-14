package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Messages;
import io.mailtrap.api.abstractions.classes.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.ForwardMessageRequest;
import io.mailtrap.model.request.ListMessagesQueryParams;
import io.mailtrap.model.request.UpdateMessageRequest;
import io.mailtrap.model.response.messages.*;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class MessagesImpl extends ApiResource implements Messages {

    public MessagesImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public MessageResponse getMessage(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s", accountId, inboxId, messageId),
                new RequestData(),
                MessageResponse.class
        );
    }

    @Override
    public MessageResponse updateMessage(long accountId, long inboxId, long messageId, UpdateMessageRequest request) {
        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s", accountId, inboxId, messageId),
                request,
                new RequestData(),
                MessageResponse.class
        );
    }

    @Override
    public MessageResponse deleteMessage(long accountId, long inboxId, long messageId) {
        return httpClient.delete(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s", accountId, inboxId, messageId),
                new RequestData(),
                MessageResponse.class
        );
    }

    @Override
    public List<MessageResponse> getMessages(long accountId, long inboxId, @NonNull ListMessagesQueryParams listMessagesQueryParams) {
        var queryParams = RequestData.buildQueryParams(
                entry("last_id", Optional.ofNullable(listMessagesQueryParams.getLastId())),
                entry("page", Optional.ofNullable(listMessagesQueryParams.getPage())),
                entry("search", Optional.ofNullable(listMessagesQueryParams.getSearch())));

        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages", accountId, inboxId),
                new RequestData(queryParams),
                MessageResponse.class
        );
    }

    @Override
    public ForwardMessageResponse forwardMessage(long accountId, long inboxId, long messageId, ForwardMessageRequest request) {
        return httpClient.post(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/forward", accountId, inboxId, messageId),
                request,
                new RequestData(),
                ForwardMessageResponse.class
        );
    }

    @Override
    public MessageSpamScoreResponse getSpamScore(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/spam_report", accountId, inboxId, messageId),
                new RequestData(),
                MessageSpamScoreResponse.class
        );
    }

    @Override
    public MessageHtmlAnalysisResponse getMessageHtmlAnalysis(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/analyze", accountId, inboxId, messageId),
                new RequestData(),
                MessageHtmlAnalysisResponse.class
        );
    }

    @Override
    public String getTextMessage(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/body.txt", accountId, inboxId, messageId),
                new RequestData(),
                String.class
        );
    }

    @Override
    public String getRawMessage(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/body.raw", accountId, inboxId, messageId),
                new RequestData(),
                String.class
        );
    }

    @Override
    public String getMessageSource(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/body.htmlsource", accountId, inboxId, messageId),
                new RequestData(),
                String.class
        );
    }

    @Override
    public String getHtmlMessage(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/body.html", accountId, inboxId, messageId),
                new RequestData(),
                String.class
        );
    }

    @Override
    public String getMessageAsEml(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/body.eml", accountId, inboxId, messageId),
                new RequestData(),
                String.class
        );
    }

    @Override
    public MessageHeadersResponse getMailHeaders(long accountId, long inboxId, long messageId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/mail_headers", accountId, inboxId, messageId),
                new RequestData(),
                MessageHeadersResponse.class
        );
    }
}
