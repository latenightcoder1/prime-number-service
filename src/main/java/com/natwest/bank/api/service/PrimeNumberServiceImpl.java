package com.natwest.bank.api.service;

import static com.natwest.bank.api.constants.RequestParameters.DESCENDING;

import com.natwest.bank.api.cache.PrimeCacheManager;
import com.natwest.bank.api.calculator.PrimeAlgorithmFactory;
import com.natwest.bank.api.calculator.PrimeCalculator;
import com.natwest.bank.api.dto.PrimeResult;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service layer implementation i.e. bridge between controller and data provider.
 */
@Service
public final class PrimeNumberServiceImpl implements PrimeNumberService {

    /**
     * {@link PrimeCacheManager} loading cache reference.
     */
    private final PrimeCacheManager primeCacheManager;

    /**
     * {@link PrimeAlgorithmFactory} factory to identify impl to get primes .
     */
    private final PrimeAlgorithmFactory primeAlgorithmFactory;

    public PrimeNumberServiceImpl(final PrimeCacheManager primeCacheManager,
        final PrimeAlgorithmFactory primeAlgorithmFactory) {
        this.primeCacheManager = primeCacheManager;
        this.primeAlgorithmFactory = primeAlgorithmFactory;
    }

    /**
     * Populates prime result.
     *
     * @param number    {@link Integer}
     * @param algorithm {@link String}
     * @param mediaType {@link String}
     * @param sort      {@link String}
     * @return {@link PrimeResult}
     */
    @Override
    public PrimeResult getPrimesUpTo(final int number, final String algorithm,
        final String mediaType, final String sort) {
        final long startTime = System.currentTimeMillis();
        var primes = getPrimes(number, algorithm);
        primes = sortPrimes(primes, sort);
        final long timeTakenMs = System.currentTimeMillis() - startTime;
        return new PrimeResult(number, primes, mediaType, algorithm, sort, timeTakenMs);
    }

    /**
     * Get primes using algorithm or from cache.
     *
     * @param number    {@link Integer}
     * @param algorithm {@link String}
     * @return {@link List} of {@link Integer}
     */
    private List<Integer> getPrimes(int number, String algorithm) {
        final PrimeCalculator primeCalculator = primeAlgorithmFactory.getPrimeCalculationAlgorithm(
            algorithm);
        final List<Integer> primes;
        if (primeCalculator != null) {
            primes = primeCalculator.getPrimesUpTo(number);
            primeCacheManager.updateCache(number, primes);
        } else {
            primes = primeCacheManager.getPrimes(number);
        }
        return primes;
    }

    /**
     * Sort primes if needed.
     *
     * @param primes {@link List} of {@link Integer}
     * @param sort   {@link String}
     */
    private List<Integer> sortPrimes(List<Integer> primes, final String sort) {
        if (DESCENDING.equalsIgnoreCase(sort)) {
            primes = primes.stream().sorted(Comparator.reverseOrder()).toList();
        }
        return primes;
    }

}
