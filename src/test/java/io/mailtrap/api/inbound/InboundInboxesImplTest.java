package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.inbound.CreateInboundInboxRequest;
import io.mailtrap.model.request.inbound.UpdateInboundInboxRequest;
import io.mailtrap.model.response.inbound.InboundInbox;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class InboundInboxesImplTest extends BaseTest {

    private static final long FOLDER_ID = 101L;
    private static final long INBOX_ID = 201L;

    private InboundInboxes api;

    @BeforeEach
    void init() {
        final String inboxesUrl = Constants.GENERAL_HOST + "/api/inbound/folders/" + FOLDER_ID + "/inboxes";
        final String inboxUrl = inboxesUrl + "/" + INBOX_ID;

        final TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(inboxesUrl, "GET", null, "api/inbound/listInboundInboxesResponse.json"),
                DataMock.build(inboxUrl, "GET", null, "api/inbound/getInboundInboxResponse.json"),
                DataMock.build(inboxesUrl, "POST", "api/inbound/createInboundInboxRequest.json",
                        "api/inbound/createInboundInboxResponse.json"),
                DataMock.build(inboxUrl, "PATCH", "api/inbound/updateInboundInboxRequest.json",
                        "api/inbound/updateInboundInboxResponse.json"),
                DataMock.build(inboxUrl, "DELETE", null, null)
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).inboundApi().inboxes();
    }

    @Test
    void getList_returnsInboxes() {
        final List<InboundInbox> inboxes = api.getList(FOLDER_ID);

        assertNotNull(inboxes);
        assertEquals(2, inboxes.size());
        assertEquals("support@inbound-mailtrap.io", inboxes.get(0).getAddress());
        assertNull(inboxes.get(0).getDomainId());
        assertEquals(6L, inboxes.get(1).getDomainId());
    }

    @Test
    void getById_returnsInbox() {
        final InboundInbox inbox = api.getById(FOLDER_ID, INBOX_ID);

        assertNotNull(inbox);
        assertEquals(201, inbox.getId());
        assertEquals("Support inbox", inbox.getName());
        assertEquals("support@inbound-mailtrap.io", inbox.getAddress());
    }

    @Test
    void create_withDomainId_returnsCreatedInbox() {
        final InboundInbox inbox = api.create(FOLDER_ID,
                CreateInboundInboxRequest.builder().name("Custom domain inbox").domainId(6L).build());

        assertNotNull(inbox);
        assertEquals(203, inbox.getId());
        assertEquals(6L, inbox.getDomainId());
    }

    @Test
    void update_returnsUpdatedInbox() {
        final InboundInbox inbox = api.update(FOLDER_ID, INBOX_ID,
                UpdateInboundInboxRequest.builder().name("Renamed inbox").build());

        assertNotNull(inbox);
        assertEquals(201, inbox.getId());
        assertEquals("Renamed inbox", inbox.getName());
    }

    @Test
    void delete_doesNotThrow() {
        assertDoesNotThrow(() -> api.delete(FOLDER_ID, INBOX_ID));
    }
}
