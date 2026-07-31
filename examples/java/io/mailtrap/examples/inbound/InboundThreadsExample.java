package io.mailtrap.examples.inbound;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;

public class InboundThreadsExample {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long INBOX_ID = 1L;

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);
        final var threads = client.inboundApi().threads();

        // List conversation threads. Pass the previous page's last id to paginate
        // (null for the first page).
        final var page = threads.list(INBOX_ID, null);
        System.out.println("Total: " + page.getTotalCount());
        page.getData().forEach(thread -> System.out.println("  " + thread.getId() + " " + thread.getSubject()));

        if (page.getData().isEmpty()) {
            return;
        }
        final var threadId = page.getData().get(0).getId();

        // Get a single thread with its messages embedded (oldest first).
        final var thread = threads.get(INBOX_ID, threadId);
        System.out.println(thread);

        // Delete a thread.
        threads.delete(INBOX_ID, threadId);
    }
}
