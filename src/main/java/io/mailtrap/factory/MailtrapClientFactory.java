package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.*;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.client.layers.MailtrapBulkSendingApi;
import io.mailtrap.client.layers.MailtrapEmailSendingApi;
import io.mailtrap.client.layers.MailtrapEmailTestingApi;
import io.mailtrap.client.layers.MailtrapGeneralApi;
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

        return new MailtrapGeneralApi(accountAccess, accounts);
    }

    private static SendingContextHolder configureSendingContext(MailtrapConfig config) {

        return SendingContextHolder.builder()
                .sandbox(config.isSandbox())
                .inboxId(config.getInboxId())
                .bulk(config.isBulk())
                .build();
    }

    private static MailtrapEmailSendingApi createSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new SendingEmailsImpl(config, customValidator);

        return new MailtrapEmailSendingApi(emails);
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
