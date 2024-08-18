package io.mailtrap.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mailtrap.Mapper;
import io.mailtrap.model.response.account_accesses.ApiTokenSpecifier;
import io.mailtrap.model.response.account_accesses.InviteSpecifier;
import io.mailtrap.model.response.account_accesses.Specifier;
import io.mailtrap.model.response.account_accesses.UserSpecifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecifierDeserializerTest {

    private final ObjectMapper mapper = Mapper.get();

    @Test
    void testApiTokenSpecifierDeserialization() throws Exception {
        String json =  """
            {
                "id": 1,
                "name": "Token",
                "token": "xyz",
                "expires_at": "2025-01-01T00:00:00"
            }
        """;

        Specifier specifier = mapper.readValue(json, Specifier.class);

        assertInstanceOf(ApiTokenSpecifier.class, specifier);
        ApiTokenSpecifier apiTokenSpecifier = (ApiTokenSpecifier) specifier;
        assertEquals(1, apiTokenSpecifier.getId());
        assertEquals("Token", apiTokenSpecifier.getName());
        assertEquals("xyz", apiTokenSpecifier.getToken());
        assertEquals("2025-01-01T00:00:00", apiTokenSpecifier.getExpiresAt());
    }

    @Test
    void testInviteSpecifierDeserialization() throws Exception {
        String json = """
            {
                "id": 2,
                "email": "invite@example.com"
            }
        """;

        Specifier specifier = mapper.readValue(json, Specifier.class);

        assertInstanceOf(InviteSpecifier.class, specifier);
        InviteSpecifier inviteSpecifier = (InviteSpecifier) specifier;
        assertEquals(2, inviteSpecifier.getId());
        assertEquals("invite@example.com", inviteSpecifier.getEmail());
    }

    @Test
    void testUserSpecifierDeserialization() throws Exception {
        String json = """
            {
                "id": 3,
                "email": "user@example.com",
                "name": "John Doe",
                "specifier_type": "User"
            }
        """;

        Specifier specifier = mapper.readValue(json, Specifier.class);

        assertInstanceOf(UserSpecifier.class, specifier);
        UserSpecifier userSpecifier = (UserSpecifier) specifier;
        assertEquals(3, userSpecifier.getId());
        assertEquals("user@example.com", userSpecifier.getEmail());
        assertEquals("John Doe", userSpecifier.getName());
    }


    @Test
    void testMissingFields() {
        String json = """
            {
                "id": 4
            }
        """;

        JsonProcessingException exception = assertThrows(JsonProcessingException.class, () -> {
            mapper.readValue(json, Specifier.class);
        });
        assertTrue(exception.getMessage().contains("Unknown specifier or missing required fields."));
    }
}
