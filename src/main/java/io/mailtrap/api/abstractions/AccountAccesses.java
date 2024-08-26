package io.mailtrap.api.abstractions;

import io.mailtrap.model.request.ListAccountAccessQueryParams;
import io.mailtrap.model.response.account_accesses.AccountAccessResponse;
import io.mailtrap.model.response.account_accesses.RemoveAccountAccessResponse;

import java.util.List;

/**
 * Interface representing the Mailtrap Testing API for interaction with account accesses
 */
public interface AccountAccesses {

    /**
     * Get list of account accesses for which specifier_type is User or Invite.
     * Account admin/owner permissions for this endpoint to work.
     *
     * @param accountId unique account ID
     * @param params    additional query params. If specified - the endpoint will return account accesses for these resources
     * @return account access details
     */
    List<AccountAccessResponse> listUserAndInviteAccountAccesses(long accountId, ListAccountAccessQueryParams params);

    /**
     * If specifier type is User, it removes user permissions.
     * If specifier type is Invite or ApiToken, it removes specifier along with permissions.
     * <p>
     * Account admin/owner permissions for this endpoint to work
     *
     * @param accountAccessId unique account access ID
     * @param accountId       unique account ID
     * @return confirmation of successful deletion and id of the deleted access
     */
    RemoveAccountAccessResponse removeAccountAccess(long accountAccessId, long accountId);

}
