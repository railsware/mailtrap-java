package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Attachments;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.response.AttachmentResponse;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttachmentsImplTest {

    private final Long accountId = 1L;
    private final int inboxId = 2;
    private final Long messageId = 3L;
    private final Long attachmentId = 4L;

    private Attachments api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(
                        Constants.DEFAULT_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/messages/" + messageId + "/attachments",
                        "GET",
                        null,
                        """
                                [
                                  {
                                    "id": 4,
                                    "message_id": 3,
                                    "filename": "test.csv",
                                    "attachment_type": "inline",
                                    "content_type": "plain/text",
                                    "content_id": null,
                                    "transfer_encoding": null,
                                    "attachment_size": 1,
                                    "created_at": "2024-07-20T19:25:54.827Z",
                                    "updated_at": "2024-07-20T19:25:54.827Z",
                                    "attachment_human_size": "1 Byte",
                                    "download_path": "/api/accounts/1/inboxes/2/messages/3/attachments/4/download"
                                  }
                                ]"""
                ),
                DataMock.build(
                        Constants.DEFAULT_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/messages/" + messageId + "/attachments/" + attachmentId,
                        "GET",
                        null,
                        """
                                  {
                                    "id": 4,
                                    "message_id": 3,
                                    "filename": "test.csv",
                                    "attachment_type": "inline",
                                    "content_type": "plain/text",
                                    "content_id": null,
                                    "transfer_encoding": null,
                                    "attachment_size": 1,
                                    "created_at": "2024-07-20T19:25:54.827Z",
                                    "updated_at": "2024-07-20T19:25:54.827Z",
                                    "attachment_human_size": "1 Byte",
                                    "download_path": "/api/accounts/1/inboxes/2/messages/3/attachments/4/download"
                                  }
                                  """
                )
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).testingApi().attachments();
    }

    @Test
    public void getAttachmentsTest() {
        List<AttachmentResponse> attachments = api.getAttachments(accountId, inboxId, messageId, null);

        assertEquals(1, attachments.size());

        AttachmentResponse attachment = attachments.get(0);
        assertEquals(attachmentId, attachment.getId());
    }

    @Test
    public void getAttachmentTest() {
        AttachmentResponse attachment = api.getSingleAttachment(accountId, inboxId, messageId, attachmentId);

        assertNotNull(attachment);
        assertEquals(attachmentId, attachment.getId());
    }

}
