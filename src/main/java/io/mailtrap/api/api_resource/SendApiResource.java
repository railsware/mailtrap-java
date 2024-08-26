package io.mailtrap.api.api_resource;

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
        if (mail == null) {
            throw new InvalidRequestBodyException("Mail must not be null");
        }

        // All three fields can not be null - we should submit either subject and text and/or html or templateUuid
        if (StringUtils.isEmpty(mail.getSubject())
                && StringUtils.isEmpty(mail.getText())
                && StringUtils.isEmpty(mail.getHtml())
                && StringUtils.isEmpty(mail.getTemplateUuid())) {
            throw new InvalidRequestBodyException(String.format("Mail %s or %s and %s or %s or both must not be null or empty",
                    MailtrapMail.Fields.templateUuid,
                    MailtrapMail.Fields.subject,
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
            if(StringUtils.isEmpty(mail.getSubject())) {
                throw new InvalidRequestBodyException(String.format("When %s is not set - the %s must be set",
                        MailtrapMail.Fields.templateUuid,
                        MailtrapMail.Fields.subject));
            }
        }

        validateRequestBodyAndThrowException(mail);
    }
}
