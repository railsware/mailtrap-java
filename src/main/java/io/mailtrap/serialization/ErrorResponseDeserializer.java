package io.mailtrap.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.mailtrap.model.response.ErrorResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Custom deserializer for deserializing error responses from JSON.
 */
public class ErrorResponseDeserializer extends JsonDeserializer<ErrorResponse> {

    @Override
    public ErrorResponse deserialize(JsonParser p, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        List<String> errors = new ArrayList<>();

        if (node.has("errors") && node.get("errors").isArray()) {
            Iterator<JsonNode> elements = node.get("errors").elements();
            while (elements.hasNext()) {
                errors.add(elements.next().asText());
            }
        } else if (node.has("error") && node.get("error").isTextual()) {
            errors.add(node.get("error").asText());
        }

        return new ErrorResponse(errors);
    }
}