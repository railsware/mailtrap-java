package io.mailtrap;

import jakarta.validation.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper for validating objects using a javax.validation.Validator.
 */
public class CustomValidator {
    private final Validator validator;

    public CustomValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> Map<String, String> validate(T objectToValidate) {
        final var violations = validator.validate(objectToValidate);

        final var errors = new HashMap<String, String>();

        for (final var violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return errors;
    }

    public <T> String validateAndGetViolationsAsString(T objectToValidate) {
        return this.validate(objectToValidate).entrySet().stream()
                .map(violation -> violation.getKey() + "=" + violation.getValue())
                .collect(Collectors.joining("; "));
    }
}
