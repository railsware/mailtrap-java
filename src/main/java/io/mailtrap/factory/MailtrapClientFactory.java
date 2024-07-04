package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.BulkSendingApiImpl;
import io.mailtrap.api.EmailTestingApiImpl;
import io.mailtrap.api.EmailSendingApiImpl;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.client.layers.MailtrapBulkEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailTestingApiLayer;
import io.mailtrap.config.MailtrapConfig;
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

        return new MailtrapClient(sendingApi, testingApi, bulkSendingApi);
    }

    private static MailtrapEmailSendingApiLayer createSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new EmailSendingApiImpl(config, customValidator);

        return new MailtrapEmailSendingApiLayer(emails);
    }

    private static MailtrapEmailTestingApiLayer createTestingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new EmailTestingApiImpl(config, customValidator);

        return new MailtrapEmailTestingApiLayer(emails);
    }

    private static MailtrapBulkEmailSendingApiLayer createBulkSendingApi(MailtrapConfig config, CustomValidator customValidator) {
        var emails = new BulkSendingApiImpl(config, customValidator);

        return new MailtrapBulkEmailSendingApiLayer(emails);
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
