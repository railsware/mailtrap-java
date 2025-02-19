package io.mailtrap.api.permissions;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.permissions.ManagePermissionsRequest;
import io.mailtrap.model.response.permissions.ManagePermissionsResponse;
import io.mailtrap.model.response.permissions.Resource;

import java.util.List;

public class PermissionsImpl extends ApiResource implements Permissions {

    public PermissionsImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public ManagePermissionsResponse managePermissions(long accountAccessId, long accountId, ManagePermissionsRequest request) {
        return httpClient.put(
                String.format(apiHost + "/api/accounts/%s/account_accesses/%s/permissions/bulk", accountId, accountAccessId),
                request,
                new RequestData(),
                ManagePermissionsResponse.class
        );
    }

    @Override
    public List<Resource> getResources(long accountId) {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/permissions/resources", accountId),
                new RequestData(),
                Resource.class
        );
    }

}
