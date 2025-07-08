package com.natwest.bank.api.cache;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.natwest.bank.api.calculator.SieveOfEratosthenesImpl;
import com.natwest.bank.api.config.CacheProperties;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Prime cache manager class unit tests")
public class PrimeCacheManagerImplTest {

    @Mock
    private SieveOfEratosthenesImpl primeCalculator;

    @Test
    @DisplayName("With cache enabled, cache hit would be served from cache only")
    void shouldServeFromCache_onRepeatedRequest() {
        final PrimeCacheManager cacheManager = new PrimeCacheManagerImpl(
            new CacheProperties(2, 100),
            primeCalculator);

        final var primes = List.of(2, 3, 5, 7, 11, 13);
        when(primeCalculator.getPrimesUpTo(13)).thenReturn(primes);

        assertAll(
            () -> assertEquals(primes, cacheManager.getPrimes(13)),
            () -> assertEquals(primes, cacheManager.getPrimes(13)),
            () -> assertEquals(primes, cacheManager.getPrimes(13)));

        //Next request didn't calculate
        verify(primeCalculator, times(1)).getPrimesUpTo(13);
    }

    @Test
    @DisplayName("With cache disable, every request would require computation")
    void shouldComputeResult_whenCacheIsEmpty() {
        //no caching set-up
        final PrimeCacheManager cacheManager = new PrimeCacheManagerImpl(new CacheProperties(0, 0),
            primeCalculator);

        final var primes = List.of(2, 3, 5, 7, 11, 13);
        when(primeCalculator.getPrimesUpTo(13)).thenReturn(primes);

        assertAll(
            () -> assertEquals(primes, cacheManager.getPrimes(13)),
            () -> assertEquals(primes, cacheManager.getPrimes(13)),
            () -> assertEquals(primes, cacheManager.getPrimes(13)));

        verify(primeCalculator, times(3)).getPrimesUpTo(13);
    }

}
