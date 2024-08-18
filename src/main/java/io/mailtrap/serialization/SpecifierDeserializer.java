package io.mailtrap.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.mailtrap.model.response.account_accesses.ApiTokenSpecifier;
import io.mailtrap.model.response.account_accesses.InviteSpecifier;
import io.mailtrap.model.response.account_accesses.Specifier;
import io.mailtrap.model.response.account_accesses.UserSpecifier;

import java.io.IOException;

public class SpecifierDeserializer extends JsonDeserializer<Specifier> {
    @Override
    public Specifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        // Determine which Specifier to use based on the fields present
        if (node.has("token")) {
            // Fields specific to ApiToken
            return codec.treeToValue(node, ApiTokenSpecifier.class);
        } else if (node.has("email") && !node.has("name")) {
            // Fields specific to Invite
            return codec.treeToValue(node, InviteSpecifier.class);
        } else if (node.has("name") && node.has("email")) {
            // Fields specific to User
            return codec.treeToValue(node, UserSpecifier.class);
        } else {
            throw new IOException("Unknown specifier or missing required fields.");
        }
    }
}
