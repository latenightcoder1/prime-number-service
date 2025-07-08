package com.natwest.bank.api.calculator;

import static com.natwest.bank.api.constants.RequestParameters.SIEVE_OF_ERATOSTHENES;
import static com.natwest.bank.api.constants.RequestParameters.TRIAL_DIVISION;

import org.springframework.stereotype.Component;

/**
 * Factory class.
 */
@Component
public class PrimeAlgorithmFactory {

    private final PrimeCalculator trialDivisionImpl;

    private final PrimeCalculator sieveOfEratosthenesImpl;

    public PrimeAlgorithmFactory(final TrialDivisionImpl trialDivisionImpl,
        final SieveOfEratosthenesImpl sieveOfEratosthenesImpl) {
        this.trialDivisionImpl = trialDivisionImpl;
        this.sieveOfEratosthenesImpl = sieveOfEratosthenesImpl;
    }

    /**
     * Factory method to identify if explicit algorithm to be used for prime calculation.
     *
     * @param algorithm {@link String}
     * @return {@link PrimeCalculator}
     */
    public PrimeCalculator getPrimeCalculationAlgorithm(final String algorithm) {
        PrimeCalculator primeCalculator = null;
        if (SIEVE_OF_ERATOSTHENES.equalsIgnoreCase(algorithm)) {
            primeCalculator = sieveOfEratosthenesImpl;
        } else if (TRIAL_DIVISION.equalsIgnoreCase(algorithm)) {
            primeCalculator = trialDivisionImpl;
        }
        return primeCalculator;
    }
}
