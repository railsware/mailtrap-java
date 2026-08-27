package io.mailtrap.api.trackingoptouts;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.trackingoptouts.CreateTrackingOptOutRequest;
import io.mailtrap.model.request.trackingoptouts.TrackingOptOutListFilter;
import io.mailtrap.model.response.trackingoptouts.TrackingOptOut;
import io.mailtrap.model.response.trackingoptouts.TrackingOptOutListResponse;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrackingOptOutsImplTest extends BaseTest {

    private static final String OPT_OUT_ID = "64d71bf3-1276-417b-86e1-8e66f138acfe";
    private static final String BASE_URL = Constants.GENERAL_HOST + "/api/tracking_opt_outs";

    private TrackingOptOuts api;

    @BeforeEach
    public void init() {
        final TestHttpClient httpClient = new TestHttpClient(List.of(
            DataMock.build(
                BASE_URL,
                "GET", null, "api/tracking_opt_outs/listTrackingOptOuts.json"
            ),
            DataMock.build(
                BASE_URL,
                "GET", null, "api/tracking_opt_outs/listTrackingOptOuts.json",
                Map.of("email", "tracked@example.com", "start_time", "2025-01-01T00:00:00Z")
            ),
            DataMock.build(
                BASE_URL,
                "POST", "api/tracking_opt_outs/createTrackingOptOutRequest.json",
                "api/tracking_opt_outs/createTrackingOptOutResponse.json"
            ),
            DataMock.build(
                BASE_URL + "/" + OPT_OUT_ID,
                "DELETE", null, "api/tracking_opt_outs/deleteTrackingOptOut.json"
            )
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
            .httpClient(httpClient)
            .token("dummy_token")
            .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).sendingApi().trackingOptOuts();
    }

    @Test
    void test_getTrackingOptOuts() {
        final TrackingOptOutListResponse response = api.getTrackingOptOuts(null);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertNull(response.getLastId());
        assertEquals("tracked@example.com", response.getData().get(0).getEmail());
        assertEquals("example.com", response.getData().get(0).getDomainName());
        assertEquals(OPT_OUT_ID, response.getData().get(0).getId());
    }

    @Test
    void test_getTrackingOptOutsWithFilter() {
        final TrackingOptOutListResponse response = api.getTrackingOptOuts(
            TrackingOptOutListFilter.builder()
                .email("tracked@example.com")
                .startTime("2025-01-01T00:00:00Z")
                .build()
        );

        assertNotNull(response);
        assertEquals(1, response.getData().size());
    }

    @Test
    void test_createTrackingOptOut() {
        final TrackingOptOut response = api.createTrackingOptOut(
            CreateTrackingOptOutRequest.builder()
                .email("tracked@example.com")
                .domainId(12345L)
                .build()
        );

        assertNotNull(response);
        assertEquals(OPT_OUT_ID, response.getId());
        assertEquals("tracked@example.com", response.getEmail());
    }

    @Test
    void test_deleteTrackingOptOut() {
        final TrackingOptOut response = api.deleteTrackingOptOut(OPT_OUT_ID);

        assertNotNull(response);
        assertEquals(OPT_OUT_ID, response.getId());
    }

    @Test
    void test_deleteTrackingOptOutRejectsBlankId() {
        assertThrows(IllegalArgumentException.class, () -> api.deleteTrackingOptOut(" "));
        assertThrows(IllegalArgumentException.class, () -> api.deleteTrackingOptOut(null));
        assertDoesNotThrow(() -> api.deleteTrackingOptOut(OPT_OUT_ID));
    }
}
