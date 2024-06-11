package io.mailtrap.exception;

/**
 * Base custom exception. Should be parent exception for all other custom exceptions
 */
public class BaseMailtrapException extends RuntimeException {

    public BaseMailtrapException(final String errorMessage) {
        super(errorMessage);
    }

    public BaseMailtrapException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

}
