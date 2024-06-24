package io.mailtrap.testutils;

import io.mailtrap.model.request.Address;
import io.mailtrap.model.request.Attachment;
import io.mailtrap.model.request.MailtrapMail;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaseSendTest {

    private Address getAddress(String email) {
        return Address.builder()
                .email(email)
                .build();
    }

    private Attachment getAttachment() {
        return Attachment.builder()
                .filename("attachment.txt")
                .type("text/plain")
                .content("c2FtcGxlIHRleHQgaW4gdGV4dCBmaWxl")
                .build();
    }

    protected MailtrapMail createValidTestMail() {
        Address from = getAddress("sender@example.com");
        Address to = getAddress("receiver@example.com");
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
        Address from = getAddress("");
        Address to = getAddress("receiver@example.com");
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
        Address from = getAddress("sender@example.com");
        Address to = getAddress("receiver@example.com");
        Attachment attachment = getAttachment();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Message without templateUuid, text and html")
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createTestMailWithTemplateUuidAndText() {
        Address from = getAddress("sender@example.com");
        Address to = getAddress("receiver@example.com");
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
        Address from = getAddress("sender@example.com");
        Address to = getAddress("receiver@example.com");
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
        Address from = getAddress("sender@example.com");
        Address to = getAddress("receiver@example.com");
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
