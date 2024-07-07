package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.BulkSendingApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.config.SendingConfig;
import io.mailtrap.exception.InvalidRequestBodyException;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import io.mailtrap.testutils.BaseSendTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BulkSendingApiImplTest extends BaseSendTest {
    private BulkSendingApi bulkSendingApi;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(
                        Constants.BULK_SENDING_HOST + "/api/send",
                        "POST",
                        createValidTestMail().toJson(),
                        """
                                {
                                  "success": true,
                                  "message_ids": ["11111"]
                                }"""
                )
        ));

        SendingConfig build = new SendingConfig.Builder().sandbox(true).build();
        SendingConfig build1 = build.toBuilder()
                .inboxId(123)
                .build();
        MailtrapConfig testConfig = MailtrapConfig.builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .sendingConfig(build)
                .build();

        bulkSendingApi = MailtrapClientFactory.createMailtrapClient(testConfig).getBulkSendingApi().emails();
    }

    @Test
    void send_InvalidMailEmptyFromEmail_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createInvalidTestMail();

        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(mail));
        assertEquals(INVALID_REQUEST__EMPTY_BODY_FROM_EMAIL, exception.getMessage());
    }

    @Test
    void send_MailWithoutTemplateUuidAndTextAndHtml_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createTestMailWithoutTemplateUuidAndTextAndHtml();

        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(mail));
        assertEquals(TEMPLATE_UUID_OR_TEXT_OR_HTML_MUST_NOT_BE_EMPTY, exception.getMessage());
    }

    @Test
    void send_MailWithTemplateUuidAndText_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createTestMailWithTemplateUuidAndText();

        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(mail));
        assertEquals(TEMPLATE_UUID_IS_USED_TEXT_AND_HTML_SHOULD_BE_EMPTY, exception.getMessage());
    }

    @Test
    void send_MailWithTemplateUuidAndHtml_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createTestMailWithTemplateUuidAndHtml();

        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(mail));
        assertEquals(TEMPLATE_UUID_IS_USED_TEXT_AND_HTML_SHOULD_BE_EMPTY, exception.getMessage());
    }

    @Test
    void send_MailWithTemplateVariablesAndHtml_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createTestMailWithTemplateVariablesAndHtml();

        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(mail));
        assertEquals(TEMPLATE_VARIABLES_SHOULD_BE_USED_WITH_TEMPLATE_UUID, exception.getMessage());
    }

    @Test
    void send_NullableMail_ThrowsInvalidRequestBodyException() {
        // Assert
        InvalidRequestBodyException exception = assertThrows(InvalidRequestBodyException.class, () -> bulkSendingApi.send(null));
        assertEquals(MAIL_MUST_NOT_BE_NULL, exception.getMessage());
    }

    @Test
    void send_ValidMail_SuccessResponse() {
        // Set up test data
        var mail = createValidTestMail();

        // Perform call
        SendResponse response = bulkSendingApi.send(mail);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("11111", response.getMessageIds().get(0));
    }
}
