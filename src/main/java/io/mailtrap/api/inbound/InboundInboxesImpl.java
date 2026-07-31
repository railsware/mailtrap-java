package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.inbound.CreateInboundInboxRequest;
import io.mailtrap.model.request.inbound.UpdateInboundInboxRequest;
import io.mailtrap.model.response.inbound.InboundInbox;

import java.util.List;

public class InboundInboxesImpl extends ApiResource implements InboundInboxes {

    public InboundInboxesImpl(final MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<InboundInbox> getList(final long folderId) {
        return httpClient.getList(
            String.format(apiHost + "/api/inbound/folders/%d/inboxes", folderId),
            new RequestData(),
            InboundInbox.class
        );
    }

    @Override
    public InboundInbox getById(final long folderId, final long inboxId) {
        return httpClient.get(
            String.format(apiHost + "/api/inbound/folders/%d/inboxes/%d", folderId, inboxId),
            new RequestData(),
            InboundInbox.class
        );
    }

    @Override
    public InboundInbox create(final long folderId, final CreateInboundInboxRequest request) {
        return httpClient.post(
            String.format(apiHost + "/api/inbound/folders/%d/inboxes", folderId),
            request,
            new RequestData(),
            InboundInbox.class
        );
    }

    @Override
    public InboundInbox update(final long folderId, final long inboxId, final UpdateInboundInboxRequest request) {
        return httpClient.patch(
            String.format(apiHost + "/api/inbound/folders/%d/inboxes/%d", folderId, inboxId),
            request,
            new RequestData(),
            InboundInbox.class
        );
    }

    @Override
    public void delete(final long folderId, final long inboxId) {
        httpClient.delete(
            String.format(apiHost + "/api/inbound/folders/%d/inboxes/%d", folderId, inboxId),
            new RequestData(),
            Void.class
        );
    }
}
