package io.mailtrap.factory;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.ProductionSendApiImpl;
import io.mailtrap.api.SandboxSendApiImpl;
import io.mailtrap.client.MailtrapClient;
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

        var sendApi = new ProductionSendApiImpl(config, customValidator);
        var sandboxSendApi = new SandboxSendApiImpl(config, customValidator);

        return new MailtrapClient(sendApi, sandboxSendApi);
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
