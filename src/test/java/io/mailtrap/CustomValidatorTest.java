package io.mailtrap;

import io.mailtrap.model.request.Address;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final CustomValidator customValidator = new CustomValidator(validator);

    @Test
    void validate_WithValidObject_ShouldReturnEmptyMap() {
        Address validAddress = Address.builder()
                .email("mail+123@gmail.com")
                .build();

        Map<String, String> errors = customValidator.validate(validAddress);

        assertEquals(0, errors.size());
    }

    @Test
    void validate_WithInvalidObject_ShouldReturnErrorsMap() {
        Address invalidAddress = Address.builder()
                .email("qwerty")
                .build();

        Map<String, String> errors = customValidator.validate(invalidAddress);

        assertEquals(1, errors.size());
        assertEquals("must be a well-formed email address", errors.get("email"));
    }

    @Test
    void validateAndGetViolationsAsString_WithValidObject_ShouldReturnEmptyString() {
        Address validAddress = Address.builder()
                .email("mail+123@gmail.com")
                .build();

        String violationsAsString = customValidator.validateAndGetViolationsAsString(validAddress);

        assertEquals("", violationsAsString);
    }

    @Test
    void validateAndGetViolationsAsString_WithInvalidObject_ShouldReturnConcatenatedString() {
        Address invalidAddress = Address.builder()
                .email("qwerty")
                .build();

        String violationsAsString = customValidator.validateAndGetViolationsAsString(invalidAddress);

        assertEquals("email=must be a well-formed email address", violationsAsString);
    }

}
