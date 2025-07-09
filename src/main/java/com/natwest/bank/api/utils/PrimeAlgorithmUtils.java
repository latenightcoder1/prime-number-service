package com.natwest.bank.api.utils;

import static com.natwest.bank.api.constants.RequestParameters.SIEVE_OF_ERATOSTHENES;
import static com.natwest.bank.api.constants.RequestParameters.TRIAL_DIVISION;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utils to process algorithm parameter.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrimeAlgorithmUtils {

    /**
     * Sanitize algorithm.
     *
     * @param algorithm {@link String}
     * @return {@link String}
     */
    public static String resolveAlgorithm(final String algorithm) {
        if (SIEVE_OF_ERATOSTHENES.equalsIgnoreCase(algorithm)) {
            return SIEVE_OF_ERATOSTHENES;
        } else if (TRIAL_DIVISION.equalsIgnoreCase(algorithm)) {
            return TRIAL_DIVISION;
        } else {
            return null;
        }
    }
}
