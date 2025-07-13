package com.natwest.bank.api.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Sieve of Eratosthenes implementation for getting primes up to a given number.
 */
@Slf4j
@Component("sieve")
@Primary
public final class SieveOfEratosthenesImpl implements PrimeCalculator {

    /**
     * Calculate prime numbers.
     *
     * <br> TC- O(n * log(logn))
     * <br> SC- O(n)
     *
     * @param number {@link Integer}
     * @return {@link List} of {@link Integer}
     */
    @Override
    public List<Integer> getPrimesUpTo(final int number) {
        if (number < 2) {
            return List.of();
        }
        return computePrimesUpto(number);
    }


    /**
     * Consider all to be primes, Update all non-primes. Remaining primes are updating non primes
     * are real primes.
     *
     * @param number {@link Integer}
     * @return {@link List} of {@link Integer}
     */
    private List<Integer> computePrimesUpto(final int number) {
        log.info("Getting all primes up to {} using Sieve Of Eratosthenes algorithm", number);
        long startTime = System.currentTimeMillis();
        final var isPrime = new boolean[number + 1];
        Arrays.fill(isPrime, true);
        updateAllNonPrimes(number, isPrime);
        final var primes = getPrimeNumbers(isPrime);
        log.info("Computed all primes up to {} using Sieve Of Eratosthenes algorithm in {} ms",
            number, System.currentTimeMillis() - startTime);
        return primes;
    }

    /**
     * Update all non prime numbers.
     *
     * @param number  {@link Integer}
     * @param isPrime {@link Arrays} of {@link Boolean}
     */
    private static void updateAllNonPrimes(final int number, final boolean[] isPrime) {
        isPrime[0] = isPrime[1] = false;
        IntStream.rangeClosed(2, (int) Math.sqrt(number)).filter(i -> isPrime[i]).forEach(
            i -> IntStream.iterate(i * i, j -> j <= number, j -> j + i)
                .forEach(j -> isPrime[j] = false));
    }

    /**
     * Get all primes.
     *
     * @param isPrime {@link Arrays} of {@link Boolean}
     * @return {@link List} of {@link Integer}
     */
    private List<Integer> getPrimeNumbers(final boolean[] isPrime) {
        return IntStream.range(0, isPrime.length).filter(i -> isPrime[i]).boxed().toList();
    }

}
