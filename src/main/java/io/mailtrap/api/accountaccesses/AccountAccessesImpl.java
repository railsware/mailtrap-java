package io.mailtrap.api.accountaccesses;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.accountaccesses.ListAccountAccessQueryParams;
import io.mailtrap.model.response.accountaccesses.AccountAccessResponse;
import io.mailtrap.model.response.accountaccesses.RemoveAccountAccessResponse;
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
