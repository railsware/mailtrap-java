package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Accounts;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.response.AccountsResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsImplTest extends BaseTest {

    private Accounts api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts",
                        "GET", null, "api/accounts/getAllAccountsResponse.json")
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).generalApi().accounts();
    }

    @Test
    void test_getAllAccounts() {
        List<AccountsResponse> accounts = api.getAllAccounts();

        assertEquals(2, accounts.size());
        assertEquals(accountId, accounts.get(0).getId());
        assertEquals(anotherAccountId, accounts.get(1).getId());
    }
}