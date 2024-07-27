package io.mailtrap.client;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.BaseMailtrapException;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import io.mailtrap.testutils.BaseSendTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailtrapClientTest extends BaseSendTest {

    private MailtrapClient mailtrapClient;

    @BeforeEach
    void setUp() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(
                        Constants.EMAIL_TESTING_SEND_HOST + "/api/send/" + INBOX_ID,
                        "POST",
                        createValidTestMail().toJson(),
                        """
                                {
                                  "success": true,
                                  "message_ids": ["11111"]
                                }"""
                ),
                DataMock.build(
                        Constants.BULK_SENDING_HOST + "/api/send",
                        "POST",
                        createValidTestMail().toJson(),
                        """
                                {
                                  "success": true,
                                  "message_ids": ["11111"]
                                }"""
                ),
                DataMock.build(
                        Constants.EMAIL_SENDING_SEND_HOST + "/api/send",
                        "POST",
                        createValidTestMail().toJson(),
                        """
                                {
                                  "success": true,
                                  "message_ids": ["11111"]
                                }"""
                )
        ));

        MailtrapConfig mailtrapConfig = new MailtrapConfig.Builder()
                .token("dummy-token")
                .httpClient(httpClient)
                .build();

        mailtrapClient = MailtrapClientFactory.createMailtrapClient(mailtrapConfig);
    }

    @Test
    void test_sendBulk_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();

        mailtrapClient.switchToBulkSendingApi();

        // Call
        SendResponse response = mailtrapClient.send(mailtrapMail);

        // Assert
        assertEquals("11111", response.getMessageIds().get(0));
    }

    @Test
    void test_sendSandbox_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();

        mailtrapClient.switchToEmailTestingApi(INBOX_ID);

        // Call
        SendResponse response = mailtrapClient.send(mailtrapMail);

        // Assert
        assertEquals("11111", response.getMessageIds().get(0));
    }

    @Test
    void test_send_success() {
        // Set up test data
        MailtrapMail mailtrapMail = createValidTestMail();

        // Call
        SendResponse response = mailtrapClient.send(mailtrapMail);

        // Assert
        assertEquals("11111", response.getMessageIds().get(0));
    }

    @Test
    void test_bulkAndSandboxTrue_ThrowsBaseMailtrapException() {
        // Set up test data and call
        BaseMailtrapException exception = assertThrows(BaseMailtrapException.class, () -> new MailtrapConfig.Builder()
                .bulk(true)
                .sandbox(true)
                .build());

        // Assert
        assertEquals(BULK_AND_SANDBOX_TRUE_IN_SENDING_CONFIG, exception.getMessage());
    }

    @Test
    void test_sandboxTrueAndInboxIdIsNull_ThrowsBaseMailtrapException() {
        // Set up test data and call
        BaseMailtrapException exception = assertThrows(BaseMailtrapException.class, () -> new MailtrapConfig.Builder()
                .sandbox(true)
                .inboxId(null)
                .build());

        // Assert
        assertEquals(INBOX_ID_REQUIRED, exception.getMessage());
    }

}
