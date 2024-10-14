package io.mailtrap.api.sending_domains;

import io.mailtrap.model.request.sending_domains.CreateSendingDomainRequest;
import io.mailtrap.model.request.sending_domains.SendingDomainsSetupInstructionsRequest;
import io.mailtrap.model.response.sending_domains.SendingDomainsResponse;

import java.util.List;

public interface SendingDomains {

    /**
     * Create a sending domain
     *
     * @param accountId unique account ID
     * @param request   request data
     * @return Attributes of created sending domain
     */
    SendingDomainsResponse create(long accountId, CreateSendingDomainRequest request);

    /**
     * Get sending domains and their statuses
     *
     * @param accountId unique account ID
     * @return domains with their attributes, DNS records, statuses
     */
    List<SendingDomainsResponse> getSendingDomains(long accountId);

    /**
     * Get domain data and it's status
     *
     * @param accountId       unique account ID
     * @param sendingDomainId unique domain ID
     * @return domain attributes, DNS records, status
     */
    SendingDomainsResponse getSendingDomain(long accountId, long sendingDomainId);

    /**
     * Send sending domain setup instructions
     *
     * @param accountId       unique account ID
     * @param sendingDomainId unique domain ID
     * @param request         request data
     */
    void sendSendingDomainsSetupInstructions(long accountId, long sendingDomainId, SendingDomainsSetupInstructionsRequest request);
}
