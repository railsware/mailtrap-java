package io.mailtrap.client.layers.wrapper;

import io.mailtrap.api.abstractions.BulkSendingApi;
import io.mailtrap.api.abstractions.EmailSendingApi;
import io.mailtrap.api.abstractions.EmailTestingApi;
import io.mailtrap.config.SendingConfig;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import io.mailtrap.testutils.BaseSendTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class MailtrapSendingWrapperTest extends BaseSendTest {

    private SendingConfig sendingConfig;
    private EmailSendingApi sendingApi;
    private EmailTestingApi testingApi;
    private BulkSendingApi bulkSendingApi;
    private MailtrapSendingWrapper mailtrapSendingWrapper;

    @BeforeEach
    void setUp() {
        sendingConfig = mock(SendingConfig.class);
        sendingApi = mock(EmailSendingApi.class);
        testingApi = mock(EmailTestingApi.class);
        bulkSendingApi = mock(BulkSendingApi.class);

        mailtrapSendingWrapper = new MailtrapSendingWrapper(sendingConfig, sendingApi, testingApi, bulkSendingApi);
    }

    @Test
    void testSendBulk() {
        MailtrapMail mailtrapMail = new MailtrapMail();
        SendResponse expectedResponse = new SendResponse();

        when(sendingConfig.isBulk()).thenReturn(true);
        when(bulkSendingApi.send(mailtrapMail)).thenReturn(expectedResponse);

        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail);

        verify(bulkSendingApi).send(mailtrapMail);
        verify(sendingApi, never()).send(mailtrapMail);
        verify(testingApi, never()).send(any(MailtrapMail.class), anyInt());
        assertSame(expectedResponse, response);
    }

    @Test
    void testSendSandbox() {
        MailtrapMail mailtrapMail = new MailtrapMail();
        SendResponse expectedResponse = new SendResponse();

        when(sendingConfig.isBulk()).thenReturn(false);
        when(sendingConfig.isSandbox()).thenReturn(true);
        when(sendingConfig.getInboxId()).thenReturn(123); // Assuming getInboxId method exists
        when(testingApi.send(mailtrapMail, 123)).thenReturn(expectedResponse);

        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail);

        verify(testingApi).send(mailtrapMail, 123);
        verify(sendingApi, never()).send(mailtrapMail);
        verify(bulkSendingApi, never()).send(mailtrapMail);
        assertSame(expectedResponse, response);
    }

    @Test
    void testSendProduction() {
        MailtrapMail mailtrapMail = new MailtrapMail();
        SendResponse expectedResponse = new SendResponse();

        when(sendingConfig.isBulk()).thenReturn(false);
        when(sendingConfig.isSandbox()).thenReturn(false);
        when(sendingApi.send(mailtrapMail)).thenReturn(expectedResponse);

        SendResponse response = mailtrapSendingWrapper.send(mailtrapMail);

        verify(sendingApi).send(mailtrapMail);
        verify(testingApi, never()).send(any(MailtrapMail.class), anyInt());
        verify(bulkSendingApi, never()).send(mailtrapMail);
        assertSame(expectedResponse, response);
    }
}
