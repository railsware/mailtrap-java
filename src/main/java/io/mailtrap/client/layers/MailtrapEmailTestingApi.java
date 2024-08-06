package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.Attachments;
import io.mailtrap.api.abstractions.TestingEmails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Testing functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapEmailTestingApi {
    private final TestingEmails emails;
    private final Attachments attachments;
}
