package io.mailtrap.client.layers.wrapper;

import io.mailtrap.api.abstractions.BulkSendingApi;
import io.mailtrap.api.abstractions.EmailSendingApi;
import io.mailtrap.api.abstractions.EmailTestingApi;
import io.mailtrap.config.SendingConfig;
import io.mailtrap.exception.BaseMailtrapException;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import io.mailtrap.testutils.BaseSendTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MailtrapSendingWrapperTest extends BaseSendTest {

    private SendingConfig.Builder sendingConfigBuilder;
    private EmailSendingApi sendingApi;
    private EmailTestingApi testingApi;
    private BulkSendingApi bulkSendingApi;
    private MailtrapSendingWrapper mailtrapSendingWrapper;

    @BeforeEach
    void setUp() {
        sendingConfigBuilder = new SendingConfig.Builder()
                .inboxId(INBOX_ID);

        sendingApi = mock(EmailSendingApi.class);
        testingApi = mock(EmailTestingApi.class);
        bulkSendingApi = mock(BulkSendingApi.class);

        mailtrapSendingWrapper = new MailtrapSendingWrapper(sendingApi, testingApi, bulkSendingApi);
    }

    @Test
    void test_sendBulk_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();
        var expectedResponse = successfulResponse("123");

        SendingConfig config = sendingConfigBuilder.bulk(true).build();

        when(bulkSendingApi.send(mailtrapMail)).thenReturn(expectedResponse);

        // Call
        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail, config);

        // Assert
        verify(bulkSendingApi).send(mailtrapMail);
        verify(sendingApi, never()).send(mailtrapMail);
        verify(testingApi, never()).send(any(MailtrapMail.class), anyInt());
        assertSame(expectedResponse, response);
    }

    @Test
    void test_sendSandbox_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();
        SendResponse expectedResponse = successfulResponse("321");

        SendingConfig config = sendingConfigBuilder.sandbox(true).build();

        when(testingApi.send(same(mailtrapMail), eq(INBOX_ID))).thenReturn(expectedResponse);

        // Call
        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail, config);

        // Assert
        verify(testingApi).send(same(mailtrapMail), eq(INBOX_ID));
        verify(sendingApi, never()).send(mailtrapMail);
        verify(bulkSendingApi, never()).send(mailtrapMail);
        assertSame(expectedResponse, response);
    }

    @Test
    void test_send_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();
        SendResponse expectedResponse = successfulResponse("123");

        SendingConfig config = sendingConfigBuilder.build();
        when(sendingApi.send(mailtrapMail)).thenReturn(expectedResponse);

        // Call
        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail, config);

        // Assert
        verify(sendingApi).send(mailtrapMail);
        verify(testingApi, never()).send(any(MailtrapMail.class), anyInt());
        verify(bulkSendingApi, never()).send(mailtrapMail);
        assertSame(expectedResponse, response);
    }

    @Test
    void test_sendWithoutConfig_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();
        SendResponse expectedResponse = successfulResponse("123");

        when(sendingApi.send(mailtrapMail)).thenReturn(expectedResponse);

        // Call
        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail);

        // Assert
        verify(sendingApi).send(mailtrapMail);
        verify(testingApi, never()).send(any(MailtrapMail.class), anyInt());
        verify(bulkSendingApi, never()).send(mailtrapMail);
        assertSame(expectedResponse, response);
    }

    @Test
    void test_bulkAndSandboxTrue_ThrowsBaseMailtrapException() {
        // Set up test data and call
        BaseMailtrapException exception = assertThrows(BaseMailtrapException.class, () -> sendingConfigBuilder
                .bulk(true)
                .sandbox(true)
                .build());

        // Assert
        assertEquals(BULK_AND_SANDBOX_TRUE_IN_SENDING_CONFIG, exception.getMessage());
    }

    @Test
    void test_sandboxTrueAndInboxIdIsNull_ThrowsBaseMailtrapException() {
        // Set up test data and call
        BaseMailtrapException exception = assertThrows(BaseMailtrapException.class, () -> sendingConfigBuilder
                .sandbox(true)
                .inboxId(null)
                .build());

        // Assert
        assertEquals(INBOX_ID_REQUIRED, exception.getMessage());
    }

}
