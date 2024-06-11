package io.mailtrap.exception.http;

/**
 * Specific exception class representing a 4xx HTTP-related error.
 */
public class HttpClientException extends HttpException {
    public HttpClientException(String errorMessage, int statusCode) {
        super(errorMessage, statusCode);
    }
}
