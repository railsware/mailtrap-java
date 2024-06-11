package io.mailtrap.api.abstractions;

import io.mailtrap.CustomValidator;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.InvalidRequestBodyException;
import io.mailtrap.model.request.MailtrapMail;

/**
 * Abstract class representing a resource for sending emails via Mailtrap API.
 */
public abstract class SendApiResource extends ApiResource {

    /**
     * The custom validator used for validating email request bodies.
     */
    protected final CustomValidator customValidator;

    protected SendApiResource(MailtrapConfig config, CustomValidator customValidator) {
        super(config);
        this.customValidator = customValidator;
    }

    /**
     * Validates the request body of an email message and throws an exception if it is invalid.
     *
     * @param mail The email message to be validated.
     * @throws InvalidRequestBodyException If the request body is invalid.
     */
    protected void validateRequestBodyOrThrowException(MailtrapMail mail) throws InvalidRequestBodyException {
        if (mail == null) {
            throw new InvalidRequestBodyException("Mail must not be null");
        }

        if ((mail.getText() == null || mail.getText().isEmpty()) && (mail.getHtml() == null || mail.getHtml().isEmpty())) {
            throw new InvalidRequestBodyException("Mail text or html or both must not be null or empty");
        }

        String violations = customValidator.validateAndGetViolationsAsString(mail);

        if (!violations.isEmpty()) {
            throw new InvalidRequestBodyException("Invalid request body. Violations: " + violations);
        }
    }
}
