package io.mailtrap.examples.inbound;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.inbound.CreateInboundInboxRequest;
import io.mailtrap.model.request.inbound.UpdateInboundInboxRequest;

public class InboundInboxesExample {

    private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
    private static final long FOLDER_ID = Long.parseLong(System.getenv("MAILTRAP_INBOUND_FOLDER_ID"));

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);
        final var inboxes = client.inboundApi().inboxes();

        // List inboxes in a folder.
        final var allInboxes = inboxes.getList(FOLDER_ID);
        System.out.println(allInboxes);

        // Create a Mailtrap-hosted inbox (omit the domain ID). To create a
        // custom-domain catch-all inbox, pass .domainId(<verified sending domain id>).
        final var created = inboxes.create(FOLDER_ID,
                CreateInboundInboxRequest.builder().name("Support inbox").build());
        System.out.println(created);

        // Get an inbox by ID.
        final var inbox = inboxes.getById(FOLDER_ID, created.getId());
        System.out.println(inbox);

        // Rename an inbox.
        final var updated = inboxes.update(FOLDER_ID, created.getId(),
                UpdateInboundInboxRequest.builder().name("Support inbox (renamed)").build());
        System.out.println(updated);

        // Delete an inbox.
        inboxes.delete(FOLDER_ID, created.getId());
    }
}
