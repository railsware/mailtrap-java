package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.EmailTestingApi;
import io.mailtrap.config.MailtrapConfig;
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

class EmailTestingApiImplTest extends BaseSendTest {

    private final int inboxId = 1;
    private EmailTestingApi sendApi;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(
                        Constants.EMAIL_TESTING_SEND_HOST + "/api/send/" + inboxId,
                        "POST",
                        createValidTestMail().toJson(),
                        """
                                {
                                  "success": true,
                                  "message_ids": ["11111"]
                                }"""
                )
        ));

        MailtrapConfig testConfig = MailtrapConfig.builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        sendApi = MailtrapClientFactory.createMailtrapClient(testConfig).getTestingApi().emails();
    }

    @Test
    void send_InvalidMail_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = createInvalidTestMail();

        // Assert
        assertThrows(InvalidRequestBodyException.class, () -> sendApi.send(mail, inboxId));
    }

    @Test
    void send_NullableMail_ThrowsInvalidRequestBodyException() {
        // Assert
        assertThrows(InvalidRequestBodyException.class, () -> sendApi.send(null, inboxId));
    }

    @Test
    void send_BothTextAndHtmlAreNullInvalidMail_ThrowsInvalidRequestBodyException() {
        // Set up invalid data
        MailtrapMail mail = MailtrapMail.builder().build();

        // Assert
        assertThrows(InvalidRequestBodyException.class, () -> sendApi.send(mail, inboxId));
    }

    @Test
    void send_ValidMail_SuccessResponse() {
        // Set up test data
        var mail = createValidTestMail();

        // Perform call
        SendResponse response = sendApi.send(mail, inboxId);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("11111", response.getMessageIds().get(0));
    }

}