package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.account_accesses.AccountAccessesImpl;
import io.mailtrap.api.accounts.AccountsImpl;
import io.mailtrap.api.attachments.AttachmentsImpl;
import io.mailtrap.api.billing.BillingImpl;
import io.mailtrap.api.bulk_emails.BulkEmailsImpl;
import io.mailtrap.api.inboxes.InboxesImpl;
import io.mailtrap.api.messages.MessagesImpl;
import io.mailtrap.api.permissions.PermissionsImpl;
import io.mailtrap.api.projects.ProjectsImpl;
import io.mailtrap.api.sending_domains.SendingDomainsImpl;
import io.mailtrap.api.sending_emails.SendingEmailsImpl;
import io.mailtrap.api.testing_emails.TestingEmailsImpl;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.client.api.MailtrapBulkSendingApi;
import io.mailtrap.client.api.MailtrapEmailSendingApi;
import io.mailtrap.client.api.MailtrapEmailTestingApi;
import io.mailtrap.client.api.MailtrapGeneralApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.util.SendingContextHolder;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

/**
 * Factory class for creating instances of {@link MailtrapClient}.
 */
public final class MailtrapClientFactory {

    private MailtrapClientFactory() {
    }

    /**
     * Creates a new {@link MailtrapClient} instance.
     *
     * @param config The configuration for creating the Mailtrap client.
     * @return A new Mailtrap client instance.
     */
    public static MailtrapClient createMailtrapClient(MailtrapConfig config) {
        var customValidator = createValidator();

        var sendingApi = createSendingApi(config, customValidator);
        var testingApi = createTestingApi(config, customValidator);
        var bulkSendingApi = createBulkSendingApi(config, customValidator);
        var generalApi = createGeneralApi(config);

        var sendingContextHolder = configureSendingContext(config);

        return new MailtrapClient(sendingApi, testingApi, bulkSendingApi, generalApi, sendingContextHolder);
    }

    private static MailtrapGeneralApi createGeneralApi(MailtrapConfig config) {
        var accountAccess = new AccountAccessesImpl(config);
        var accounts = new AccountsImpl(config);
        var billing = new BillingImpl(config);
        var permissions = new PermissionsImpl(config);

        return new MailtrapGeneralApi(accountAccess, accounts, billing, permissions);
    }

    private static MailtrapEmailSendingApi createSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new SendingEmailsImpl(config, customValidator);
        var domains = new SendingDomainsImpl(config);

        return new MailtrapEmailSendingApi(emails, domains);
    }

    private static MailtrapEmailTestingApi createTestingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new TestingEmailsImpl(config, customValidator);
        var attachments = new AttachmentsImpl(config);
        var inboxes = new InboxesImpl(config, customValidator);
        var projects = new ProjectsImpl(config, customValidator);
        var messages = new MessagesImpl(config);

        return new MailtrapEmailTestingApi(emails, attachments, inboxes, projects, messages);
    }

    private static MailtrapBulkSendingApi createBulkSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new BulkEmailsImpl(config, customValidator);

        return new MailtrapBulkSendingApi(emails);
    }

    private static SendingContextHolder configureSendingContext(MailtrapConfig config) {

        return SendingContextHolder.builder()
                .sandbox(config.isSandbox())
                .inboxId(config.getInboxId())
                .bulk(config.isBulk())
                .build();
    }

    /**
     * Creates a new instance of {@link CustomValidator} using the default validator factory.
     */
    private static CustomValidator createValidator() {
        // Wrapped into try-with-resources to ensure that factory's resources are properly closed
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            return new CustomValidator(factory.getValidator());
        }
    }
}
