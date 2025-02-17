package io.mailtrap.api.apiresource;

import io.mailtrap.CustomValidator;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.InvalidRequestBodyException;
import io.mailtrap.model.request.emails.MailtrapMail;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract class representing a resource for sending emails via Mailtrap API.
 */
public abstract class SendApiResource extends ApiResourceWithValidation {

    protected SendApiResource(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
    }

    /**
     * Validates the request body of an email message and throws an exception if it is invalid.
     *
     * @param mail The email message to be validated.
     * @throws InvalidRequestBodyException If the request body is invalid.
     */
    protected void validateRequestBodyOrThrowException(MailtrapMail mail) throws InvalidRequestBodyException {
        // Check if the mail object itself is null
        if (mail == null) {
            throw new InvalidRequestBodyException("Mail must not be null");
        }

        // Check if all three subject, text, and html are empty
        boolean isSubjectTextHtmlEmpty = StringUtils.isEmpty(mail.getSubject())
                && StringUtils.isEmpty(mail.getText())
                && StringUtils.isEmpty(mail.getHtml());

        // Validate depending on whether the templateUuid is set
        if (StringUtils.isEmpty(mail.getTemplateUuid())) {
            // Validation for the scenario where templateUuid is not provided
            validateWithoutTemplate(mail, isSubjectTextHtmlEmpty);
        } else {
            // Validation for the scenario where templateUuid is provided
            validateWithTemplate(mail);
        }

        // Additional validation logic (assumed to be provided by the user)
        validateRequestBodyAndThrowException(mail);
    }

    private void validateWithoutTemplate(MailtrapMail mail, boolean isSubjectTextHtmlEmpty) throws InvalidRequestBodyException {
        // Ensure that at least subject, text, or html is provided if templateUuid is not set
        if (isSubjectTextHtmlEmpty) {
            throw new InvalidRequestBodyException("Mail must have subject and either text or html when templateUuid is not provided");
        }

        // Ensure templateVariables are not used if templateUuid is not set
        if (MapUtils.isNotEmpty(mail.getTemplateVariables())) {
            throw new InvalidRequestBodyException("Mail templateVariables must only be used with templateUuid");
        }

        // Ensure the subject is not empty
        if (StringUtils.isEmpty(mail.getSubject())) {
            throw new InvalidRequestBodyException("Subject must not be null or empty");
        }
    }

    private void validateWithTemplate(MailtrapMail mail) throws InvalidRequestBodyException {
        // Ensure that subject, text, and html are not used when templateUuid is set
        if (StringUtils.isNotEmpty(mail.getText()) || StringUtils.isNotEmpty(mail.getHtml()) || StringUtils.isNotEmpty(mail.getSubject())) {
            throw new InvalidRequestBodyException("When templateUuid is used, subject, text, and html must not be used");
        }
    }
}
