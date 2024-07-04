package io.mailtrap.api.abstractions.classes;

import io.mailtrap.CustomValidator;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.InvalidRequestBodyException;
import io.mailtrap.model.request.MailtrapMail;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

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

        // All three fields can not be null - we should submit either text and/or html or templateUuid
        if (StringUtils.isEmpty(mail.getText()) && StringUtils.isEmpty(mail.getHtml()) && StringUtils.isEmpty(mail.getTemplateUuid())) {
            throw new InvalidRequestBodyException(String.format("Mail %s or %s or %s or %s and %s must not be null or empty",
                    MailtrapMail.Fields.templateUuid,
                    MailtrapMail.Fields.text,
                    MailtrapMail.Fields.html,
                    MailtrapMail.Fields.text,
                    MailtrapMail.Fields.html));
        }

        if (StringUtils.isNotEmpty(mail.getTemplateUuid())) {
            // When templateUuid is set
            if (StringUtils.isNotEmpty(mail.getText()) ||
                    StringUtils.isNotEmpty(mail.getHtml()) ||
                    StringUtils.isNotEmpty(mail.getSubject())) {
                throw new InvalidRequestBodyException(String.format("When %s is used - %s and %s and %s must not be used",
                        MailtrapMail.Fields.templateUuid,
                        MailtrapMail.Fields.text,
                        MailtrapMail.Fields.subject,
                        MailtrapMail.Fields.html));
            }
        } else {
            // When templateUuid is not set - templateVariables should not be used
            if (MapUtils.isNotEmpty(mail.getTemplateVariables())) {
                throw new InvalidRequestBodyException(String.format("Mail %s must only be used with %s",
                        MailtrapMail.Fields.templateVariables,
                        MailtrapMail.Fields.templateUuid));
            }
        }

        String violations = customValidator.validateAndGetViolationsAsString(mail);

        if (!violations.isEmpty()) {
            throw new InvalidRequestBodyException("Invalid request body. Violations: " + violations);
        }
    }
}
