package io.mailtrap.examples.testing;

import io.mailtrap.client.api.MailtrapEmailTestingApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.account_accesses.ListMessagesQueryParams;
import io.mailtrap.model.request.messages.ForwardMessageRequest;
import io.mailtrap.model.request.messages.UpdateMessageRequest;
import io.mailtrap.model.response.inboxes.InboxResponse;
import io.mailtrap.model.response.messages.*;

import java.util.List;

public class Messages {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final String FORWARD_EMAIL_TO = "recipient@domain.com";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapEmailTestingApi testingClient = MailtrapClientFactory.createMailtrapClient(config).testingApi();

        List<InboxResponse> inboxes = testingClient.inboxes().getInboxes(ACCOUNT_ID);

        if (!inboxes.isEmpty()) {
            long firstInboxId = inboxes.get(0).getId();

            List<MessageResponse> messages = testingClient.messages().getMessages(ACCOUNT_ID, firstInboxId, ListMessagesQueryParams.empty());

            if (!messages.isEmpty()) {
                long firstMessageId = messages.get(0).getId();

                MessageResponse getMessage = testingClient.messages().getMessage(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(getMessage);

                MessageHtmlAnalysisResponse htmlAnalysis = testingClient.messages().getMessageHtmlAnalysis(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(htmlAnalysis);

                String htmlMessage = testingClient.messages().getHtmlMessage(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(htmlMessage);

                String textMessage = testingClient.messages().getTextMessage(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(textMessage);

                String rawMessage = testingClient.messages().getRawMessage(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(rawMessage);

                String messageAsEml = testingClient.messages().getMessageAsEml(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(messageAsEml);

                String messageSource = testingClient.messages().getMessageSource(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(messageSource);

                MessageHeadersResponse mailHeaders = testingClient.messages().getMailHeaders(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(mailHeaders);

                MessageSpamScoreResponse spamScore = testingClient.messages().getSpamScore(ACCOUNT_ID, firstInboxId, firstMessageId);
                System.out.println(spamScore);

                MessageResponse updateMessage = testingClient.messages().updateMessage(ACCOUNT_ID, firstInboxId, firstMessageId, new UpdateMessageRequest(new UpdateMessageRequest.MessageUpdateData("true")));
                System.out.println(updateMessage);

                ForwardMessageResponse forwardMessage = testingClient.messages().forwardMessage(ACCOUNT_ID, firstInboxId, firstMessageId, new ForwardMessageRequest(FORWARD_EMAIL_TO));
                System.out.println(forwardMessage);
            }
        }
    }
}
