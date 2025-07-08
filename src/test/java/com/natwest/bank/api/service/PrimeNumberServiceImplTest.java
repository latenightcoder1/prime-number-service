package com.natwest.bank.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.natwest.bank.api.cache.PrimeCacheManagerImpl;
import com.natwest.bank.api.calculator.PrimeAlgorithmFactory;
import com.natwest.bank.api.calculator.SieveOfEratosthenesImpl;
import com.natwest.bank.api.calculator.TrialDivisionImpl;
import com.natwest.bank.api.dto.PrimeResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Prime number service layer implementation unit tests")
public class PrimeNumberServiceImplTest {

    private PrimeNumberService primeNumberService;

    @Mock
    private PrimeCacheManagerImpl primeCacheManager;

    @Mock
    private PrimeAlgorithmFactory primeAlgorithmFactory;

    private static final List<Integer> PRIMES_UPTO_10 = List.of(2, 3, 5, 7);
    private static final String SIEVE = "sieve";
    private static final String TRIAL = "trial";
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String JSON = "json";
    private static final int TEN = 10;

    @BeforeEach
    public void setUp() {
        primeNumberService = new PrimeNumberServiceImpl(primeCacheManager, primeAlgorithmFactory);
    }

    @Test
    @DisplayName("Prime numbers would be calculated using sieve algorithm")
    void shouldGetPrimesUsingSieveAndUpdateCache_whenSieveRequested() {
        when(primeAlgorithmFactory.getPrimeCalculationAlgorithm(SIEVE)).thenReturn(
            new SieveOfEratosthenesImpl());

        final PrimeResult primeResult = primeNumberService.getPrimesUpTo(TEN, SIEVE, JSON, ASC);

        assertEquals(primeResult.primes(), PRIMES_UPTO_10);
        verify(primeCacheManager).updateCache(TEN, PRIMES_UPTO_10);
        verify(primeCacheManager, never()).getPrimes(anyInt());
    }

    @Test
    @DisplayName("Primes would be returned in desc order after calculating using sieve algorithm")
    void shouldGetPrimesUsingSieveAndUpdateCache_whenAlgorithmSieveAndSortDesc() {
        when(primeAlgorithmFactory.getPrimeCalculationAlgorithm(SIEVE)).thenReturn(
            new SieveOfEratosthenesImpl());

        final PrimeResult primeResult = primeNumberService.getPrimesUpTo(TEN, SIEVE, JSON, DESC);

        assertEquals(primeResult.primes(), List.of(7, 5, 3, 2));
        verify(primeCacheManager).updateCache(TEN, PRIMES_UPTO_10);
        verify(primeCacheManager, never()).getPrimes(anyInt());
    }

    @Test
    @DisplayName("Prime numbers would be calculated using trial division algorithm")
    void shouldGetPrimesUsingTrialDivisionAndUpdateCache_whenTrialRequested() {
        when(primeAlgorithmFactory.getPrimeCalculationAlgorithm(TRIAL)).thenReturn(
            new TrialDivisionImpl());

        final PrimeResult primeResult = primeNumberService.getPrimesUpTo(TEN, TRIAL, JSON, ASC);

        assertEquals(primeResult.primes(), PRIMES_UPTO_10);
        verify(primeCacheManager).updateCache(TEN, PRIMES_UPTO_10);
        verify(primeCacheManager, never()).getPrimes(anyInt());
    }

    @Test
    @DisplayName("Prime numbers would be fetched through cache manager")
    void shouldGetPrimesUsingCacheManager_whenNoAlgorithm() {
        when(primeCacheManager.getPrimes(TEN)).thenReturn(PRIMES_UPTO_10);

        final PrimeResult primeResult = primeNumberService.getPrimesUpTo(TEN, null, JSON, ASC);

        assertEquals(primeResult.primes(), PRIMES_UPTO_10);
        verify(primeCacheManager).getPrimes(anyInt());
        verify(primeCacheManager, never()).updateCache(TEN, PRIMES_UPTO_10);
    }

}
