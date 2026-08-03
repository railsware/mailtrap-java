package io.mailtrap.api.apitokens;

import com.fasterxml.jackson.databind.JsonNode;
import io.mailtrap.Constants;
import io.mailtrap.Mapper;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.exception.http.HttpClientException;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.http.CustomHttpClient;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.AccessLevel;
import io.mailtrap.model.ResourceType;
import io.mailtrap.model.request.apitokens.ApiTokenResource;
import io.mailtrap.model.request.apitokens.CreateApiTokenRequest;
import io.mailtrap.model.request.apitokens.ResetApiTokenRequest;
import io.mailtrap.model.request.apitokens.TokenExpiration;
import io.mailtrap.model.response.apitokens.ApiToken;
import io.mailtrap.model.response.apitokens.ApiTokenWithToken;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiTokensImplTest extends BaseTest {

    private final long apiTokenId = 12345L;
    private final long resetWithBodyApiTokenId = 54321L;

    private ApiTokens api;

    @BeforeEach
    public void init() {
        final TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens",
                        "GET", null, "api/apitokens/listApiTokensResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens",
                        "POST", "api/apitokens/createApiTokenRequest.json", "api/apitokens/createApiTokenResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens",
                        "POST", "api/apitokens/createApiTokenNeverExpiresRequest.json", "api/apitokens/createApiTokenNeverExpiresResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens",
                        "POST", "api/apitokens/createApiTokenWithExpirationRequest.json", "api/apitokens/createApiTokenWithExpirationResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens/" + apiTokenId,
                        "GET", null, "api/apitokens/getApiTokenResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens/" + apiTokenId,
                        "DELETE", null, null),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens/" + apiTokenId + "/reset",
                        "POST", null, "api/apitokens/resetApiTokenResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens/" + resetWithBodyApiTokenId + "/reset",
                        "POST", "api/apitokens/resetApiTokenNeverExpiresRequest.json", "api/apitokens/resetApiTokenNeverExpiresResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/api_tokens/" + resetWithBodyApiTokenId + "/reset",
                        "POST", "api/apitokens/resetApiTokenWithExpirationRequest.json", "api/apitokens/resetApiTokenWithExpirationResponse.json")
        ));

        final MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).generalApi().apiTokens();
    }

    @Test
    void test_getAllApiTokens() {
        final List<ApiToken> tokens = api.getAllApiTokens(accountId);

        assertEquals(2, tokens.size());
        assertEquals(12345L, tokens.get(0).getId());
        assertEquals("x7k9", tokens.get(0).getLast4Digits());
        assertEquals(ResourceType.ACCOUNT, tokens.get(0).getResources().get(0).getResourceType());
        assertEquals(AccessLevel.ADMIN, tokens.get(0).getResources().get(0).getAccessLevel());
        assertEquals(ResourceType.INBOX, tokens.get(1).getResources().get(0).getResourceType());
        assertEquals(AccessLevel.VIEWER, tokens.get(1).getResources().get(0).getAccessLevel());
        assertNotNull(tokens.get(1).getExpiresAt());
    }

    @Test
    void test_createApiToken() throws IOException {
        final CreateApiTokenRequest request = new CreateApiTokenRequest(
                "Scratch test token",
                List.of(new ApiTokenResource(ResourceType.ACCOUNT, accountId, AccessLevel.ADMIN))
        );

        final JsonNode body = Mapper.get().readTree(request.toJson());
        assertFalse(body.has("expires_at"));

        final ApiTokenWithToken response = api.createApiToken(accountId, request);

        assertNotNull(response);
        assertEquals(12345L, response.getId());
        assertEquals("a1b2c3d4e5f6", response.getToken());
        assertEquals(AccessLevel.ADMIN, response.getResources().get(0).getAccessLevel());
        assertNull(response.getExpiresAt());
    }

    @Test
    void test_createApiToken_neverExpires() throws IOException {
        final CreateApiTokenRequest request = new CreateApiTokenRequest(
                "Never expiring token",
                TokenExpiration.never(),
                List.of(new ApiTokenResource(ResourceType.ACCOUNT, accountId, AccessLevel.ADMIN))
        );

        final JsonNode body = Mapper.get().readTree(request.toJson());
        assertTrue(body.has("expires_at"));
        assertTrue(body.get("expires_at").isNull());

        final ApiTokenWithToken response = api.createApiToken(accountId, request);

        assertNotNull(response);
        assertEquals(23456L, response.getId());
        assertEquals("neverexpires123", response.getToken());
        assertNull(response.getExpiresAt());
    }

    @Test
    void test_createApiToken_withExpiration() throws IOException {
        final CreateApiTokenRequest request = new CreateApiTokenRequest(
                "Expiring token",
                TokenExpiration.at(OffsetDateTime.parse("2027-06-01T00:00:00Z")),
                List.of(new ApiTokenResource(ResourceType.ACCOUNT, accountId, AccessLevel.ADMIN))
        );

        final JsonNode body = Mapper.get().readTree(request.toJson());
        assertEquals("2027-06-01T00:00:00Z", body.get("expires_at").asText());

        final ApiTokenWithToken response = api.createApiToken(accountId, request);

        assertNotNull(response);
        assertEquals(34567L, response.getId());
        assertEquals("expiring123", response.getToken());
        assertEquals(OffsetDateTime.parse("2027-06-01T00:00:00Z"), response.getExpiresAt());
    }

    @Test
    void test_createApiToken_invalidExpiration_throwsHttpClientException() {
        final CustomHttpClient failingHttpClient = mock(CustomHttpClient.class);
        when(failingHttpClient.post(anyString(), any(CreateApiTokenRequest.class), any(RequestData.class), eq(ApiTokenWithToken.class)))
                .thenThrow(new HttpClientException("Expires at must be no more than 5 years in the future", 422));

        final MailtrapConfig failingConfig = new MailtrapConfig.Builder()
                .httpClient(failingHttpClient)
                .token("dummy_token")
                .build();

        final ApiTokens failingApi = MailtrapClientFactory.createMailtrapClient(failingConfig).generalApi().apiTokens();

        final CreateApiTokenRequest request = new CreateApiTokenRequest(
                "Token with invalid expiration",
                TokenExpiration.at(OffsetDateTime.parse("2050-01-01T00:00:00Z")),
                List.of(new ApiTokenResource(ResourceType.ACCOUNT, accountId, AccessLevel.ADMIN))
        );

        final HttpClientException exception = assertThrows(HttpClientException.class,
                () -> failingApi.createApiToken(accountId, request));
        assertEquals(422, exception.getStatusCode());
    }

    @Test
    void test_getApiToken() {
        final ApiToken token = api.getApiToken(accountId, apiTokenId);

        assertNotNull(token);
        assertEquals(apiTokenId, token.getId());
        assertEquals("My API Token", token.getName());
        assertEquals(ResourceType.ACCOUNT, token.getResources().get(0).getResourceType());
    }

    @Test
    void test_deleteApiToken() {
        api.deleteApiToken(accountId, apiTokenId);
    }

    @Test
    void test_resetApiToken() {
        final ApiTokenWithToken response = api.resetApiToken(accountId, apiTokenId);

        assertNotNull(response);
        assertEquals(apiTokenId, response.getId());
        assertEquals("newtoken123", response.getToken());
        assertEquals("n3w0", response.getLast4Digits());
    }

    @Test
    void test_resetApiToken_neverExpires() throws IOException {
        final ResetApiTokenRequest request = new ResetApiTokenRequest(TokenExpiration.never());

        final JsonNode body = Mapper.get().readTree(request.toJson());
        assertTrue(body.has("expires_at"));
        assertTrue(body.get("expires_at").isNull());

        final ApiTokenWithToken response = api.resetApiToken(accountId, resetWithBodyApiTokenId, request);

        assertNotNull(response);
        assertEquals(resetWithBodyApiTokenId, response.getId());
        assertEquals("resetnever123", response.getToken());
        assertNull(response.getExpiresAt());
    }

    @Test
    void test_resetApiToken_withExpiration() throws IOException {
        final ResetApiTokenRequest request = new ResetApiTokenRequest(
                TokenExpiration.at(OffsetDateTime.parse("2027-06-01T00:00:00Z"))
        );

        final JsonNode body = Mapper.get().readTree(request.toJson());
        assertEquals("2027-06-01T00:00:00Z", body.get("expires_at").asText());

        final ApiTokenWithToken response = api.resetApiToken(accountId, resetWithBodyApiTokenId, request);

        assertNotNull(response);
        assertEquals(resetWithBodyApiTokenId, response.getId());
        assertEquals("resetexpiring123", response.getToken());
        assertEquals(OffsetDateTime.parse("2027-06-01T00:00:00Z"), response.getExpiresAt());
    }
}
