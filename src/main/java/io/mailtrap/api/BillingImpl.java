package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Billing;
import io.mailtrap.api.abstractions.classes.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.response.billing.BillingResponse;

public class BillingImpl extends ApiResource implements Billing {

    public BillingImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public BillingResponse getCurrentBillingCycleUsage(long accountId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/billing/usage", accountId),
                new RequestData(),
                BillingResponse.class);
    }
}
