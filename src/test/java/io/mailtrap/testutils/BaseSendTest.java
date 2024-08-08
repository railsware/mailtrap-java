package io.mailtrap.testutils;

import io.mailtrap.model.request.Address;
import io.mailtrap.model.request.Attachment;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaseSendTest {

    protected final long INBOX_ID = 4308;
    protected final String BULK_AND_SANDBOX_TRUE_IN_SENDING_CONFIG = "Bulk mode is not applicable for Testing API";
    protected final String INBOX_ID_REQUIRED = "Testing API requires inbox ID";
    protected final String INVALID_REQUEST_EMPTY_BODY_FROM_EMAIL = "Invalid request body. Violations: from.email=must not be empty";
    protected final String TEMPLATE_UUID_OR_TEXT_OR_HTML_MUST_NOT_BE_EMPTY = "Mail templateUuid or text or html or text and html must not be null or empty";
    protected final String TEMPLATE_UUID_IS_USED_TEXT_AND_HTML_SHOULD_BE_EMPTY = "When templateUuid is used - text and subject and html must not be used";
    protected final String TEMPLATE_VARIABLES_SHOULD_BE_USED_WITH_TEMPLATE_UUID = "Mail templateVariables must only be used with templateUuid";
    protected final String MAIL_MUST_NOT_BE_NULL = "Mail must not be null";

    private Address getAddress(String email, String name) {
        return new Address(email, name);
    }

    private Attachment getAttachment() {
        return Attachment.builder()
                .filename("attachment.txt")
                .type("text/plain")
                .content("c2FtcGxlIHRleHQgaW4gdGV4dCBmaWxl")
                .build();
    }

    protected SendResponse successfulResponse(String... messageIds) {
        SendResponse expectedResponse = new SendResponse();

        expectedResponse.setSuccess(true);
        expectedResponse.setMessageIds(Arrays.asList(messageIds));

        return expectedResponse;
    }

    protected MailtrapMail createValidTestMail() {
        Address from = getAddress("sender@example.com", "John Doe");
        Address to = getAddress("receiver@example.com", "Jane Doe");
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample valid mail subject")
                .text("Sample valid mail text")
                .html("<html><body>Test HTML</body></html>")
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createInvalidTestMail() {
        Address from = getAddress("", "John Doe");
        Address to = getAddress("receiver@example.com", null);
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample invalid mail subject")
                .text("Sample invalid mail text")
                .html("<html><body>Test HTML</body></html>")
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createTestMailWithoutTemplateUuidAndTextAndHtml() {
        Address from = getAddress("sender@example.com", "John Doe");
        Address to = getAddress("receiver@example.com", "Jane Doe");
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Message without templateUuid, text and html")
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createTestMailWithTemplateUuidAndText() {
        Address from = getAddress("sender@example.com", null);
        Address to = getAddress("receiver@example.com", null);
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample mail with templateUuid and text")
                .text("Sample mail text")
                .templateUuid(UUID.randomUUID().toString())
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createTestMailWithTemplateUuidAndHtml() {
        Address from = getAddress("sender@example.com", "John Doe");
        Address to = getAddress("receiver@example.com", "Jane Doe");
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample mail with templateUuid and text")
                .html("<html><body>Test HTML</body></html>")
                .templateUuid(UUID.randomUUID().toString())
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createTestMailWithTemplateVariablesAndHtml() {
        Address from = getAddress("sender@example.com", null);
        Address to = getAddress("receiver@example.com", null);
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample subject")
                .html("<html><body>Test HTML</body></html>")
                .templateVariables(Map.of("user_name", "John Doe"))
                .attachments(List.of(attachment))
                .build();
    }
}
