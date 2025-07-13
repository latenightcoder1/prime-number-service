package com.natwest.bank.api.calculator;

import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Component;

/**
 * Factory class.
 */
@Component
public class PrimeAlgorithmFactory {

    /**
     * {@link Map} of {@link String} of {@link PrimeCalculator}
     */
    private final Map<String, PrimeCalculator> primeCalculatorMap;

    public PrimeAlgorithmFactory(final Map<String, PrimeCalculator> primeCalculatorMap) {
        this.primeCalculatorMap = primeCalculatorMap;
    }

    /**
     * Factory method to identify if explicit algorithm to be used for prime calculation.
     *
     * @param algorithm {@link String}
     * @return {@link PrimeCalculator}
     */
    public PrimeCalculator getPrimeCalculationAlgorithm(final String algorithm) {
        return primeCalculatorMap.entrySet().stream()
            .filter(entry -> entry.getKey().equalsIgnoreCase(algorithm)).map(Entry::getValue)
            .findFirst().orElse(null);
    }
}
