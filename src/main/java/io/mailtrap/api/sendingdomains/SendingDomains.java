package io.mailtrap.api.sendingdomains;

import io.mailtrap.model.request.sendingdomains.CreateSendingDomainRequest;
import io.mailtrap.model.request.sendingdomains.SendingDomainsSetupInstructionsRequest;
import io.mailtrap.model.response.sendingdomains.SendingDomainsResponse;

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

    /**
     * Delete a sending domain
     *
     * @param accountId       unique account ID
     * @param sendingDomainId unique domain ID
     */
    void deleteSendingDomain(long accountId, long sendingDomainId);
}
