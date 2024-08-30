package io.mailtrap.client.api;

import io.mailtrap.api.sending_emails.SendingEmails;
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
