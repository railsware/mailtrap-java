package io.mailtrap.examples.inbound;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.inbound.CreateInboundFolderRequest;
import io.mailtrap.model.request.inbound.UpdateInboundFolderRequest;

public class InboundFoldersExample {

    private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);
        final var folders = client.inboundApi().folders();

        // List all inbound folders.
        final var allFolders = folders.getList();
        System.out.println(allFolders);

        // Create an inbound folder.
        final var created = folders.create(
                CreateInboundFolderRequest.builder().name("Support").build());
        System.out.println(created);

        // Get an inbound folder by ID.
        final var folder = folders.getById(created.getId());
        System.out.println(folder);

        // Rename an inbound folder.
        final var updated = folders.update(created.getId(),
                UpdateInboundFolderRequest.builder().name("Support (renamed)").build());
        System.out.println(updated);

        // Delete an inbound folder (removes the folder and all of its inboxes).
        folders.delete(created.getId());
    }
}
