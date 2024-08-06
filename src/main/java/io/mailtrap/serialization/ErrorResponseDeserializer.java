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
import java.util.Map;

public class ErrorResponseDeserializer extends JsonDeserializer<ErrorResponse> {

    @Override
    public ErrorResponse deserialize(JsonParser p, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        List<String> errors = new ArrayList<>();

        if (node.has("errors")) {
            JsonNode errorsNode = node.get("errors");
            if (errorsNode.isArray()) {
                Iterator<JsonNode> elements = errorsNode.elements();
                while (elements.hasNext()) {
                    errors.add(elements.next().asText());
                }
            } else if (errorsNode.isTextual()) {
                errors.add(errorsNode.asText());
            } else if (errorsNode.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = errorsNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    JsonNode fieldValue = field.getValue();
                    if (fieldValue.isArray()) {
                        Iterator<JsonNode> fieldElements = fieldValue.elements();
                        while (fieldElements.hasNext()) {
                            errors.add(fieldElements.next().asText());
                        }
                    } else if (fieldValue.isTextual()) {
                        errors.add(fieldValue.asText());
                    }
                }
            }
        } else if (node.has("error") && node.get("error").isTextual()) {
            errors.add(node.get("error").asText());
        }

        return new ErrorResponse(errors);
    }
}
