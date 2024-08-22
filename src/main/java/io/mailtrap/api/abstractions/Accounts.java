package io.mailtrap.api.abstractions;

import io.mailtrap.model.response.AccountsResponse;

import java.util.List;

/**
 * Interface representing the Mailtrap Testing API for interaction with accounts
 */
public interface Accounts {

    /**
     * Get a list of your Mailtrap accounts.
     *
     * @return the list of accounts to which the API token has access
     */
    List<AccountsResponse> getAllAccounts();

}
