package io.mailtrap.api.sendingdomains;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.sendingdomains.CreateSendingDomainRequest;
import io.mailtrap.model.request.sendingdomains.SendingDomainsSetupInstructionsRequest;
import io.mailtrap.model.response.sendingdomains.SendingDomainsResponse;

import java.util.List;

public class SendingDomainsImpl extends ApiResource implements SendingDomains {

    public SendingDomainsImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public SendingDomainsResponse create(long accountId, CreateSendingDomainRequest request) {
        return httpClient.post(
                String.format(apiHost + "/api/accounts/%s/sending_domains", accountId),
                request,
                new RequestData(),
                SendingDomainsResponse.class
        );
    }

    @Override
    public List<SendingDomainsResponse> getSendingDomains(long accountId) {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/sending_domains", accountId),
                new RequestData(),
                SendingDomainsResponse.class
        );
    }

    @Override
    public SendingDomainsResponse getSendingDomain(long accountId, long sendingDomainId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/sending_domains/%s", accountId, sendingDomainId),
                new RequestData(),
                SendingDomainsResponse.class
                );
    }

    @Override
    public void sendSendingDomainsSetupInstructions(long accountId, long sendingDomainId, SendingDomainsSetupInstructionsRequest request) {
        httpClient.post(
                String.format(apiHost + "/api/accounts/%s/sending_domains/%s/send_setup_instructions", accountId, sendingDomainId),
                request,
                new RequestData(),
                Void.class
        );
    }
}
