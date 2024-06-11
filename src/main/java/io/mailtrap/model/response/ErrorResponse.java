package io.mailtrap.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mailtrap.serialization.ErrorResponseDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(using = ErrorResponseDeserializer.class)
public class ErrorResponse {
    private List<String> errors;
}
