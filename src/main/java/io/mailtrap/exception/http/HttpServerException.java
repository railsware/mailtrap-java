package io.mailtrap.exception.http;

/**
 * Specific exception class representing a 5xx HTTP-related error.
 */
public class HttpServerException extends HttpException {
    public HttpServerException(String errorMessage, int statusCode) {
        super(errorMessage, statusCode);
    }
}
