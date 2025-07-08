package com.natwest.bank.api.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.natwest.bank.api.calculator.PrimeCalculator;
import com.natwest.bank.api.config.CacheProperties;
import com.natwest.bank.api.exception.PrimeServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Receives request from service layer and serve either from cache or from calculator.
 */
@Component
@Slf4j
public final class PrimeCacheManagerImpl implements PrimeCacheManager {

    /**
     * {@link LoadingCache} with key {@link Integer} and value all primes up to key {@link List} of
     * {@link Integer}
     */
    private final LoadingCache<Integer, List<Integer>> primeCache;

    /**
     * Initialize and define loading cache.
     *
     * @param cacheProperties {@link CacheProperties}
     * @param primeCalculator {@link PrimeCalculator}
     */
    public PrimeCacheManagerImpl(final CacheProperties cacheProperties,
        final PrimeCalculator primeCalculator) {
        this.primeCache = CacheBuilder.newBuilder()
            .expireAfterWrite(cacheProperties.expiryHours(), TimeUnit.HOURS)
            .maximumSize(cacheProperties.maxSize())
            .build(CacheLoader.from(number -> {
                log.info("Cache miss for number {}", number);
                return primeCalculator.getPrimesUpTo(number);
            }));
    }

    /**
     * Gets defensive copy of primes. Fetches from cache first
     * <br> and if not available in cache fetch from calculator, update to cache as well.
     *
     * @param number {@link Integer}
     * @return {@link List} of {@link Integer}
     */
    public List<Integer> getPrimes(final int number) {
        try {
            return new ArrayList<>(primeCache.get(number));
        } catch (final ExecutionException e) {
            log.error("An error occurred with details", e);
            throw new PrimeServiceException("Error occurred while populating prime numbers");
        }
    }

    /**
     * Update cache with a copy of passed value.
     *
     * @param key   {@link Integer}
     * @param value {@link List} of {@link Integer}
     */
    @Override
    public void updateCache(final Integer key, final List<Integer> value) {
        if (key < 2) {
            log.error("Cache update request received for number {} which is less than 2", key);
            return;
        }
        primeCache.put(key, new ArrayList<>(value));
    }
}
