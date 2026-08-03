package io.mailtrap.examples.general;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.AccessLevel;
import io.mailtrap.model.ResourceType;
import io.mailtrap.model.request.apitokens.ApiTokenResource;
import io.mailtrap.model.request.apitokens.CreateApiTokenRequest;
import io.mailtrap.model.request.apitokens.ResetApiTokenRequest;
import io.mailtrap.model.request.apitokens.TokenExpiration;

import java.time.OffsetDateTime;
import java.util.List;

public class ApiTokensExample {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
            .token(TOKEN)
            .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);

        // The full token value is returned only on creation — store it securely.
        // Expiration is optional: omit it for the server default (a 1-year default is being
        // rolled out), pass TokenExpiration.never() for a token that never expires, or pass
        // TokenExpiration.at(...) for a concrete expiration (must be in the future and no
        // more than 5 years ahead, otherwise the API responds with a 422 error).
        final var createRequest = new CreateApiTokenRequest(
            "My token",
            TokenExpiration.at(OffsetDateTime.now().plusMonths(6)),
            List.of(new ApiTokenResource(ResourceType.ACCOUNT, ACCOUNT_ID, AccessLevel.VIEWER)));

        final var createdToken = client.generalApi().apiTokens()
            .createApiToken(ACCOUNT_ID, createRequest);
        System.out.println(createdToken);

        final var tokenId = createdToken.getId();

        final var allTokens = client.generalApi().apiTokens().getAllApiTokens(ACCOUNT_ID);
        System.out.println(allTokens);

        final var token = client.generalApi().apiTokens().getApiToken(ACCOUNT_ID, tokenId);
        System.out.println(token);

        // Reset expires the existing token and returns a new one with the same permissions.
        // The new token value is only returned here. Without a request body the new token
        // gets the server default expiration; the overload with ResetApiTokenRequest sets it
        // explicitly (here: a token that never expires).
        final var resetToken = client.generalApi().apiTokens()
            .resetApiToken(ACCOUNT_ID, tokenId, new ResetApiTokenRequest(TokenExpiration.never()));
        System.out.println(resetToken);

        client.generalApi().apiTokens().deleteApiToken(ACCOUNT_ID, resetToken.getId());
    }
}
