package io.mailtrap.client.api;

import io.mailtrap.api.attachments.Attachments;
import io.mailtrap.api.messages.Messages;
import io.mailtrap.api.inboxes.Inboxes;
import io.mailtrap.api.projects.Projects;
import io.mailtrap.api.testing_emails.TestingEmails;
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
    private final Inboxes inboxes;
    private final Projects projects;
    private final Messages messages;
}
