package io.mailtrap.exception;

/**
 * Custom exception wrapper for JSON serializing/deserializing exceptions
 */
public class JsonException extends BaseMailtrapException {

    public JsonException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
