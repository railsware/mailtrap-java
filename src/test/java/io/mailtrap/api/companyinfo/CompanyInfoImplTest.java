package io.mailtrap.api.companyinfo;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.companyinfo.CreateCompanyInfoRequest;
import io.mailtrap.model.request.companyinfo.UpdateCompanyInfoRequest;
import io.mailtrap.model.response.companyinfo.CompanyInfoResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompanyInfoImplTest extends BaseTest {

    private final long domainId = 12345L;

    private CompanyInfo companyInfo;

    @BeforeEach
    public void init() {
        final String companyInfoUrl = Constants.GENERAL_HOST + "/api/domains/" + domainId + "/company_info";

        final TestHttpClient httpClient = new TestHttpClient(List.of(
            DataMock.build(
                companyInfoUrl,
                "GET", null, "api/company_info/companyInfoResponse.json"
            ),
            DataMock.build(
                companyInfoUrl,
                "POST", "api/company_info/createCompanyInfoRequest.json", "api/company_info/companyInfoResponse.json"
            ),
            DataMock.build(
                companyInfoUrl,
                "PATCH", "api/company_info/updateCompanyInfoRequest.json", "api/company_info/companyInfoResponse.json"
            )
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
            .httpClient(httpClient)
            .token("dummy_token")
            .build();

        companyInfo = MailtrapClientFactory.createMailtrapClient(testConfig).sendingApi().companyInfo();
    }

    @Test
    void test_getCompanyInfo() {
        final CompanyInfoResponse response = companyInfo.getCompanyInfo(domainId);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("Mailtrap", response.getData().getName());
        assertEquals("San Francisco", response.getData().getCity());
        assertEquals("94105", response.getData().getZipCode());
        assertEquals("business", response.getData().getInfoLevel());
    }

    @Test
    void test_createCompanyInfo() {
        final CompanyInfoResponse response = companyInfo.createCompanyInfo(
            domainId,
            new CreateCompanyInfoRequest(
                CreateCompanyInfoRequest.CompanyInfoData.builder()
                    .name("Mailtrap")
                    .address("123 Main St")
                    .city("San Francisco")
                    .country("US")
                    .zipCode("94105")
                    .websiteUrl("https://mailtrap.io")
                    .infoLevel("business")
                    .build()
            )
        );

        assertNotNull(response);
        assertEquals("Mailtrap", response.getData().getName());
    }

    @Test
    void test_updateCompanyInfo() {
        final CompanyInfoResponse response = companyInfo.updateCompanyInfo(
            domainId,
            new UpdateCompanyInfoRequest(
                UpdateCompanyInfoRequest.CompanyInfoData.builder()
                    .city("New York")
                    .zipCode("10001")
                    .build()
            )
        );

        assertNotNull(response);
        assertEquals("Mailtrap", response.getData().getName());
    }
}
