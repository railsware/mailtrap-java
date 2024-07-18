package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.BulkSendingApiImpl;
import io.mailtrap.api.EmailSendingApiImpl;
import io.mailtrap.api.EmailTestingApiImpl;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.client.layers.wrapper.MailtrapSendingWrapper;
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

        var sendWrapper = createSendingWrapper(config, customValidator);

        return new MailtrapClient(sendWrapper);
    }

    private static MailtrapSendingWrapper createSendingWrapper(MailtrapConfig config, CustomValidator customValidator) {
        var sendingApi = new EmailSendingApiImpl(config, customValidator);
        var testingApi = new EmailTestingApiImpl(config, customValidator);
        var bulkSendingApi = new BulkSendingApiImpl(config, customValidator);

        return new MailtrapSendingWrapper(sendingApi, testingApi, bulkSendingApi);
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
