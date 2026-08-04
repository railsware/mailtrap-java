package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.response.emaillogs.MessageStatus;
import io.mailtrap.model.response.inbound.InboundMessageDirection;
import io.mailtrap.model.response.inbound.InboundThread;
import io.mailtrap.model.response.inbound.InboundThreadsListResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InboundThreadsImplTest extends BaseTest {

    private static final long INBOX_ID = 201L;
    private static final String THREAD_ID = "thr_1";

    private InboundThreads api;

    @BeforeEach
    void init() {
        final String threadsUrl = Constants.GENERAL_HOST + "/api/inbound/inboxes/" + INBOX_ID + "/threads";
        final String threadUrl = threadsUrl + "/" + THREAD_ID;

        final TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(threadsUrl, "GET", null, "api/inbound/listInboundThreadsResponse.json"),
                DataMock.build(threadsUrl, "GET", null, "api/inbound/listInboundThreadsResponse.json",
                        Map.of("last_id", "thr_2")),
                DataMock.build(threadUrl, "GET", null, "api/inbound/getInboundThreadResponse.json"),
                DataMock.build(threadUrl, "DELETE", null, null)
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).inboundApi().threads();
    }

    @Test
    void list_withoutCursor_returnsPage() {
        final InboundThreadsListResponse response = api.list(INBOX_ID, null);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(2, response.getTotalCount());
        assertEquals("thr_2", response.getLastId());
        assertEquals("Support request", response.getData().get(0).getSubject());
        assertEquals(3, response.getData().get(0).getMessageCount());
    }

    @Test
    void list_withCursor_returnsPage() {
        final InboundThreadsListResponse response = api.list(INBOX_ID, "thr_2");

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    void get_returnsThreadWithMessages() {
        final InboundThread thread = api.get(INBOX_ID, THREAD_ID);

        assertNotNull(thread);
        assertEquals("thr_1", thread.getId());
        assertEquals("Support request", thread.getSubject());
        assertNotNull(thread.getMessages());
        assertEquals(2, thread.getMessages().size());
        assertEquals(InboundMessageDirection.INBOUND, thread.getMessages().get(0).getDirection());
        assertEquals(InboundMessageDirection.OUTBOUND, thread.getMessages().get(1).getDirection());
        assertEquals(MessageStatus.DELIVERED, thread.getMessages().get(1).getDeliveryStatus());
    }

    @Test
    void delete_doesNotThrow() {
        assertDoesNotThrow(() -> api.delete(INBOX_ID, THREAD_ID));
    }
}
