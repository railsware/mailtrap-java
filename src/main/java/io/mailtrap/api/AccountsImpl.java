package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Accounts;
import io.mailtrap.api.abstractions.classes.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.response.AccountsResponse;

import java.util.List;

public class AccountsImpl extends ApiResource implements Accounts {

    public AccountsImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<AccountsResponse> getAllAccounts() {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts"),
                new RequestData(),
                AccountsResponse.class
        );
    }
}
