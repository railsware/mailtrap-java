package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.inbound.CreateInboundFolderRequest;
import io.mailtrap.model.request.inbound.UpdateInboundFolderRequest;
import io.mailtrap.model.response.inbound.InboundFolder;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InboundFoldersImplTest extends BaseTest {

    private static final long FOLDER_ID = 101L;

    private InboundFolders api;

    @BeforeEach
    void init() {
        final String foldersUrl = Constants.GENERAL_HOST + "/api/inbound/folders";
        final String folderUrl = foldersUrl + "/" + FOLDER_ID;

        final TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(foldersUrl, "GET", null, "api/inbound/listInboundFoldersResponse.json"),
                DataMock.build(folderUrl, "GET", null, "api/inbound/getInboundFolderResponse.json"),
                DataMock.build(foldersUrl, "POST", "api/inbound/createInboundFolderRequest.json",
                        "api/inbound/createInboundFolderResponse.json"),
                DataMock.build(folderUrl, "PATCH", "api/inbound/updateInboundFolderRequest.json",
                        "api/inbound/updateInboundFolderResponse.json"),
                DataMock.build(folderUrl, "DELETE", null, null)
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).inboundApi().folders();
    }

    @Test
    void getList_returnsFolders() {
        final List<InboundFolder> folders = api.getList();

        assertNotNull(folders);
        assertEquals(2, folders.size());
        assertEquals(101, folders.get(0).getId());
        assertEquals("Support", folders.get(0).getName());
        assertEquals("Sales", folders.get(1).getName());
    }

    @Test
    void getById_returnsFolder() {
        final InboundFolder folder = api.getById(FOLDER_ID);

        assertNotNull(folder);
        assertEquals(101, folder.getId());
        assertEquals("Support", folder.getName());
    }

    @Test
    void create_returnsCreatedFolder() {
        final InboundFolder folder = api.create(
                CreateInboundFolderRequest.builder().name("Support").build());

        assertNotNull(folder);
        assertEquals(103, folder.getId());
        assertEquals("Support", folder.getName());
    }

    @Test
    void update_returnsUpdatedFolder() {
        final InboundFolder folder = api.update(FOLDER_ID,
                UpdateInboundFolderRequest.builder().name("Renamed folder").build());

        assertNotNull(folder);
        assertEquals(101, folder.getId());
        assertEquals("Renamed folder", folder.getName());
    }

    @Test
    void delete_doesNotThrow() {
        assertDoesNotThrow(() -> api.delete(FOLDER_ID));
    }
}
