package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.SendingEmails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Sending functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapEmailSendingApi {
    private final SendingEmails emails;
}
