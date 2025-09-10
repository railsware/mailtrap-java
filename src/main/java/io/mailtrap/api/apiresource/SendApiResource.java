package io.mailtrap.api.apiresource;

import io.mailtrap.CustomValidator;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.InvalidRequestBodyException;
import io.mailtrap.model.request.emails.MailtrapBatchMail;
import io.mailtrap.model.request.emails.MailtrapMail;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Abstract class representing a resource for sending emails via Mailtrap API.
 */
public abstract class SendApiResource extends ApiResourceWithValidation {

    protected SendApiResource(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
    }

    protected void assertBatchMailNotNull(MailtrapBatchMail batchMail) {
        if (batchMail == null) {
            throw new InvalidRequestBodyException("BatchMail must not be null");
        }
        if (batchMail.getRequests() == null || batchMail.getRequests().isEmpty()) {
            throw new InvalidRequestBodyException("BatchMail.requests must not be null or empty");
        }
        if (batchMail.getRequests().stream().anyMatch(Objects::isNull)) {
            throw new InvalidRequestBodyException("BatchMail.requests must not contain null items");
        }
    }

    /**
     * Validates the request body of an email message and throws an exception if it is invalid.
     *
     * @param mail The email message to be validated.
     * @throws InvalidRequestBodyException If the request body is invalid.
     */
    protected void validateMailPayload(MailtrapMail mail) throws InvalidRequestBodyException {
        // Check if the mail object itself is null
        if (mail == null) {
            throw new InvalidRequestBodyException("Mail must not be null");
        }

        // Check if all three subject, text, and html are empty
        boolean isSubjectTextHtmlEmpty = StringUtils.isBlank(mail.getSubject())
            && StringUtils.isBlank(mail.getText())
            && StringUtils.isBlank(mail.getHtml());

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
        if (StringUtils.isBlank(mail.getSubject())) {
            throw new InvalidRequestBodyException("Subject must not be null or empty");
        }

        // Ensure at least one of text or html is present
        if (StringUtils.isBlank(mail.getText()) && StringUtils.isBlank(mail.getHtml())) {
            throw new InvalidRequestBodyException("Mail must have subject and either text or html when templateUuid is not provided");
        }
    }

    private void validateWithTemplate(MailtrapMail mail) throws InvalidRequestBodyException {
        // Ensure that subject, text, and html are not used when templateUuid is set
        if (StringUtils.isNotEmpty(mail.getText()) || StringUtils.isNotEmpty(mail.getHtml()) || StringUtils.isNotEmpty(mail.getSubject())) {
            throw new InvalidRequestBodyException("When templateUuid is used, subject, text, and html must not be used");
        }
    }
}
