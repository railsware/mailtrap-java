package io.mailtrap.client.api;

import io.mailtrap.api.bulkemails.BulkEmails;
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
