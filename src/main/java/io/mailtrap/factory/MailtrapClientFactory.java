package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.accountaccesses.AccountAccessesImpl;
import io.mailtrap.api.accounts.AccountsImpl;
import io.mailtrap.api.attachments.AttachmentsImpl;
import io.mailtrap.api.billing.BillingImpl;
import io.mailtrap.api.bulkemails.BulkEmailsImpl;
import io.mailtrap.api.contactimports.ContactImportsImpl;
import io.mailtrap.api.contactlists.ContactListsImpl;
import io.mailtrap.api.contacts.ContactsImpl;
import io.mailtrap.api.inboxes.InboxesImpl;
import io.mailtrap.api.messages.MessagesImpl;
import io.mailtrap.api.permissions.PermissionsImpl;
import io.mailtrap.api.projects.ProjectsImpl;
import io.mailtrap.api.sendingdomains.SendingDomainsImpl;
import io.mailtrap.api.sendingemails.SendingEmailsImpl;
import io.mailtrap.api.testingemails.TestingEmailsImpl;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.client.api.*;
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
        final var customValidator = createValidator();

        final var sendingApi = createSendingApi(config, customValidator);
        final var testingApi = createTestingApi(config, customValidator);
        final var bulkSendingApi = createBulkSendingApi(config, customValidator);
        final var generalApi = createGeneralApi(config);
        final var contactsApi = createContactsApi(config, customValidator);

        final var sendingContextHolder = configureSendingContext(config);

        return new MailtrapClient(sendingApi, testingApi, bulkSendingApi, generalApi, contactsApi, sendingContextHolder);
    }

    private static MailtrapContactsApi createContactsApi(MailtrapConfig config, CustomValidator customValidator) {
        final var contactLists = new ContactListsImpl(config);
        final var contacts = new ContactsImpl(config);
        final var contactImports = new ContactImportsImpl(config, customValidator);

        return new MailtrapContactsApi(contactLists, contacts, contactImports);
    }

    private static MailtrapGeneralApi createGeneralApi(MailtrapConfig config) {
        final var accountAccess = new AccountAccessesImpl(config);
        final var accounts = new AccountsImpl(config);
        final var billing = new BillingImpl(config);
        final var permissions = new PermissionsImpl(config);

        return new MailtrapGeneralApi(accountAccess, accounts, billing, permissions);
    }

    private static MailtrapEmailSendingApi createSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        final var emails = new SendingEmailsImpl(config, customValidator);
        final var domains = new SendingDomainsImpl(config);

        return new MailtrapEmailSendingApi(emails, domains);
    }

    private static MailtrapEmailTestingApi createTestingApi(MailtrapConfig config, CustomValidator customValidator) {
        final var emails = new TestingEmailsImpl(config, customValidator);
        final var attachments = new AttachmentsImpl(config);
        final var inboxes = new InboxesImpl(config, customValidator);
        final var projects = new ProjectsImpl(config, customValidator);
        final var messages = new MessagesImpl(config);

        return new MailtrapEmailTestingApi(emails, attachments, inboxes, projects, messages);
    }

    private static MailtrapBulkSendingApi createBulkSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        final var emails = new BulkEmailsImpl(config, customValidator);

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
