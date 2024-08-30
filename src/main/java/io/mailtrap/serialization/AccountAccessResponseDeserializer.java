package io.mailtrap.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mailtrap.model.response.Permission;
import io.mailtrap.model.response.account_accesses.*;

import java.io.IOException;
import java.util.List;

/**
 * Custom deserializer for {@link AccountAccessResponse} that handles the deserialization
 * of complex fields, including dynamic mapping of the {@code specifier} field based on
 * the {@code specifier_type} field.
 *
 * <p>This deserializer uses an {@link ObjectMapper} to convert JSON nodes into Java objects.
 * The deserialization process includes:
 * <ul>
 *   <li>Extracting and converting simple fields such as {@code id}, {@code resources},
 *       and {@code permissions}.</li>
 *   <li>Dynamically deserializing the {@code specifier} field based on the value of the
 *       {@code specifier_type} field.</li>
 * </ul>
 *
 * <p>The {@code specifier} field is mapped to a specific subclass of {@link Specifier}
 * (e.g., {@link UserSpecifier}, {@link InviteSpecifier}, {@link ApiTokenSpecifier})
 * based on the value of {@code specifier_type}.
 *
 * <p>The deserialized {@link AccountAccessResponse} object is then constructed and returned.
 */
public class AccountAccessResponseDeserializer extends JsonDeserializer<AccountAccessResponse> {

    @Override
    public AccountAccessResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode rootNode = mapper.readTree(p);

        // Extract the simple fields
        long id = rootNode.get("id").asLong();
        JsonNode resourcesNode = rootNode.get("resources");
        JsonNode permissionsNode = rootNode.get("permissions");

        // Deserialize the simple fields
        var resources = mapper.convertValue(resourcesNode, new TypeReference<List<Resource>>() {
        });
        var permissions = mapper.treeToValue(permissionsNode, Permission.class);

        // Extract and handle the specifier_type and specifier fields
        String specifierTypeStr = rootNode.get("specifier_type").asText();

        SpecifierType specifierType = SpecifierType.fromValue(specifierTypeStr);

        JsonNode specifierNode = rootNode.get("specifier");

        Specifier specifier = switch (specifierType) {
            case USER -> mapper.treeToValue(specifierNode, UserSpecifier.class);
            case INVITE -> mapper.treeToValue(specifierNode, InviteSpecifier.class);
            case API_TOKEN -> mapper.treeToValue(specifierNode, ApiTokenSpecifier.class);
        };

        // Construct the AccountAccessResponse object
        var response = new AccountAccessResponse();

        response.setId(id);
        response.setSpecifier(specifier);
        response.setSpecifierType(specifierType);
        response.setResources(resources);
        response.setPermission(permissions);

        return response;
    }
}
