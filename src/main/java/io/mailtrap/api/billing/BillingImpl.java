package io.mailtrap.api.billing;

import io.mailtrap.Constants;
import io.mailtrap.api.api_resource.ApiResource;
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
