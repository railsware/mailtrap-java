package io.mailtrap.api.account_accesses;

import io.mailtrap.Constants;
import io.mailtrap.api.api_resource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.account_accesses.ListAccountAccessQueryParams;
import io.mailtrap.model.response.account_accesses.AccountAccessResponse;
import io.mailtrap.model.response.account_accesses.RemoveAccountAccessResponse;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class AccountAccessesImpl extends ApiResource implements AccountAccesses {

    public AccountAccessesImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<AccountAccessResponse> listUserAndInviteAccountAccesses(long accountId, @NonNull ListAccountAccessQueryParams params) {
        var queryParams = RequestData.buildQueryParams(
                entry("domain_uuids", Optional.ofNullable(params.getDomainUuids())),
                entry("inbox_ids", Optional.ofNullable(params.getInboxIds())),
                entry("project_ids", Optional.ofNullable(params.getProjectIds())));

        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/account_accesses", accountId),
                new RequestData(queryParams),
                AccountAccessResponse.class
        );
    }

    @Override
    public RemoveAccountAccessResponse removeAccountAccess(long accountAccessId, long accountId) {
        return httpClient.delete(
                String.format(apiHost + "/api/accounts/%s/account_accesses/%s", accountId, accountAccessId),
                new RequestData(),
                RemoveAccountAccessResponse.class
        );
    }
}
