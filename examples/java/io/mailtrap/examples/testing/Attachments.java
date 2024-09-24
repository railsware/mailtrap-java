package io.mailtrap.examples.testing;

import io.mailtrap.client.api.MailtrapEmailTestingApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.account_accesses.ListMessagesQueryParams;
import io.mailtrap.model.response.attachment.AttachmentResponse;
import io.mailtrap.model.response.inboxes.InboxResponse;
import io.mailtrap.model.response.messages.MessageResponse;

import java.util.List;

public class Attachments {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapEmailTestingApi testingClient = MailtrapClientFactory.createMailtrapClient(config).testingApi();

        List<InboxResponse> inboxes = testingClient.inboxes().getInboxes(ACCOUNT_ID);

        if (!inboxes.isEmpty()) {
            InboxResponse firstInbox = inboxes.get(0);

            List<MessageResponse> messages = testingClient.messages().getMessages(ACCOUNT_ID, firstInbox.getId(), ListMessagesQueryParams.empty());

            if (!messages.isEmpty()) {
                MessageResponse firstMessage = messages.get(0);

                List<AttachmentResponse> attachments = testingClient.attachments().getAttachments(ACCOUNT_ID, firstInbox.getId(), firstMessage.getId(), null);

                if (!attachments.isEmpty()) {
                    AttachmentResponse firstAttachment = attachments.get(0);

                    System.out.println(testingClient.attachments().getSingleAttachment(ACCOUNT_ID, firstInbox.getId(), firstMessage.getId(), firstAttachment.getId()));
                }
            }
        }
    }
}
