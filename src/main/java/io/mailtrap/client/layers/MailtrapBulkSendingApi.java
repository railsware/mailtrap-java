package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.BulkEmails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Bulk Sending functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapBulkSendingApi {
    private final BulkEmails emails;
}
