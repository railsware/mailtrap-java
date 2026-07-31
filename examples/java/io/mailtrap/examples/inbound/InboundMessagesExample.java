package io.mailtrap.examples.inbound;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.inbound.InboundForwardRequest;
import io.mailtrap.model.request.inbound.InboundReplyRequest;

import java.util.List;

public class InboundMessagesExample {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long INBOX_ID = 1L;

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);
        final var messages = client.inboundApi().messages();

        // List received messages. Pass the previous page's last id to paginate
        // (null for the first page).
        final var page = messages.list(INBOX_ID, null);
        System.out.println("Total: " + page.getTotalCount());
        page.getData().forEach(msg -> System.out.println("  " + msg.getId() + " " + msg.getSubject()));

        if (page.getData().isEmpty()) {
            return;
        }
        final var messageId = page.getData().get(0).getId();

        // Get a single message with its body and attachment download URLs.
        final var message = messages.get(INBOX_ID, messageId);
        System.out.println(message);

        // Reply to a message (sends a real email to the original sender).
        final var reply = messages.reply(INBOX_ID, messageId, InboundReplyRequest.builder()
                .subject("Re: Support request")
                .text("Thanks for reaching out!")
                .html("<p>Thanks for reaching out!</p>")
                .build());
        System.out.println(reply);

        // Reply to a message and copy the original's other recipients.
        final var replyAll = messages.replyAll(INBOX_ID, messageId, InboundReplyRequest.builder()
                .text("Looping everyone in.")
                .build());
        System.out.println(replyAll);

        // Forward a message to new recipients (at least one `to` is required).
        final var forward = messages.forward(INBOX_ID, messageId, InboundForwardRequest.builder()
                .to(List.of(new Address("colleague@example.com")))
                .text("Please take a look.")
                .build());
        System.out.println(forward);

        // Delete a message.
        messages.delete(INBOX_ID, messageId);
    }
}
