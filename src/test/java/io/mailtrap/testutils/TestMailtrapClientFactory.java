package io.mailtrap.testutils;

import io.mailtrap.CustomValidator;
import io.mailtrap.api.ProductionSendApiImpl;
import io.mailtrap.api.SandboxSendApiImpl;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import jakarta.validation.Validation;

public class TestMailtrapClientFactory {

    public static MailtrapClient getTestClient(MailtrapConfig testConfig) {

        CustomValidator testValidator = new CustomValidator(Validation.buildDefaultValidatorFactory().getValidator());

        var productionSendApi = new ProductionSendApiImpl(testConfig, testValidator);
        var sandboxSendApi = new SandboxSendApiImpl(testConfig, testValidator);

        return new MailtrapClient(productionSendApi, sandboxSendApi);
    }
}
