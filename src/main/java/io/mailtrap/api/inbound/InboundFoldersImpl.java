package io.mailtrap.api.inbound;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.inbound.CreateInboundFolderRequest;
import io.mailtrap.model.request.inbound.UpdateInboundFolderRequest;
import io.mailtrap.model.response.inbound.InboundFolder;

import java.util.List;

public class InboundFoldersImpl extends ApiResource implements InboundFolders {

    public InboundFoldersImpl(final MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<InboundFolder> getList() {
        return httpClient.getList(
            apiHost + "/api/inbound/folders",
            new RequestData(),
            InboundFolder.class
        );
    }

    @Override
    public InboundFolder getById(final long folderId) {
        return httpClient.get(
            String.format(apiHost + "/api/inbound/folders/%d", folderId),
            new RequestData(),
            InboundFolder.class
        );
    }

    @Override
    public InboundFolder create(final CreateInboundFolderRequest request) {
        return httpClient.post(
            apiHost + "/api/inbound/folders",
            request,
            new RequestData(),
            InboundFolder.class
        );
    }

    @Override
    public InboundFolder update(final long folderId, final UpdateInboundFolderRequest request) {
        return httpClient.patch(
            String.format(apiHost + "/api/inbound/folders/%d", folderId),
            request,
            new RequestData(),
            InboundFolder.class
        );
    }

    @Override
    public void delete(final long folderId) {
        httpClient.delete(
            String.format(apiHost + "/api/inbound/folders/%d", folderId),
            new RequestData(),
            Void.class
        );
    }
}
