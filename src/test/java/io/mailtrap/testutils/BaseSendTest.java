package io.mailtrap.testutils;

import io.mailtrap.model.request.Address;
import io.mailtrap.model.request.Attachment;
import io.mailtrap.model.request.MailtrapMail;

import java.util.List;

public class BaseSendTest {
    protected MailtrapMail createValidTestMail() {
        Address from = Address.builder()
                .email("sender@example.com")
                .build();

        Address to = Address.builder()
                .email("sender@example.com")
                .build();

        Attachment attachment = Attachment.builder()
                .filename("attachment.txt")
                .type("text/plain")
                .content("c2FtcGxlIHRleHQgaW4gdGV4dCBmaWxl")
                .build();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample subject")
                .text("Sample mail text")
                .html("<html><body>Test HTML</body></html>")
                .attachments(List.of(attachment))
                .build();
    }

    protected MailtrapMail createInvalidTestMail() {
        Address from = Address.builder()
                .email("")
                .build();

        Address to = Address.builder()
                .email("sender@example.com")
                .build();

        Attachment attachment = Attachment.builder()
                .filename("attachment.txt")
                .type("text/plain")
                .content("c2FtcGxlIHRleHQgaW4gdGV4dCBmaWxl")
                .build();

        return MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample subject")
                .text("Sample mail text")
                .html("<html><body>Test HTML</body></html>")
                .attachments(List.of(attachment))
                .build();
    }
}
