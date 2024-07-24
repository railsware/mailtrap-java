package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.BulkSendingApi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Bulk Sending functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapBulkEmailSendingApiLayer {
    private final BulkSendingApi emails;
}
