package com.natwest.bank.api.exception;

/**
 * Service Known Runtime exception.
 */
public class PrimeServiceException extends RuntimeException {

    public PrimeServiceException(final String message) {
        super(message);
    }
}
