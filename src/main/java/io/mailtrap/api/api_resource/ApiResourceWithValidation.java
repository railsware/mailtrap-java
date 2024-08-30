package io.mailtrap.api.api_resource;

import io.mailtrap.CustomValidator;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.InvalidRequestBodyException;

public abstract class ApiResourceWithValidation extends ApiResource {

    /**
     * The custom validator used for validating email request bodies.
     */
    protected final CustomValidator customValidator;

    protected ApiResourceWithValidation(MailtrapConfig config, CustomValidator customValidator) {
        super(config);
        this.customValidator = customValidator;
    }

    protected <T> void validateRequestBodyAndThrowException(T object) {
        String violations = customValidator.validateAndGetViolationsAsString(object);

        if (!violations.isEmpty()) {
            throw new InvalidRequestBodyException("Invalid request body. Violations: " + violations);
        }
    }
}
