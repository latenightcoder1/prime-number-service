package com.natwest.bank.api.calculator;

import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Trial Division implementation for getting primes up to a given number.
 */
@Component("trial")
@Slf4j
public final class TrialDivisionImpl implements PrimeCalculator {

    /**
     * Calculate prime numbers.
     *
     * <br> TC- O(n*sqrt(n))
     * <br> SC- O(1)
     *
     * @param number {@link Integer}
     * @return {@link List} of {@link Integer}
     */
    @Override
    public List<Integer> getPrimesUpTo(final int number) {
        if (number < 2) {
            return List.of();
        }
        log.info("Getting all primes up to {} using Trial Division algorithm", number);
        long startTime = System.currentTimeMillis();
        final var primes = IntStream.rangeClosed(2, number).filter(this::isPrime).boxed().toList();
        log.info("Computed all primes up to {} using Trial Division algorithm in {} ms",
            number, System.currentTimeMillis() - startTime);
        return primes;
    }

    /**
     * Is number divisible by any number <= square root of number.
     *
     * @param number {@link Integer}
     * @return {@link Boolean}
     */
    private boolean isPrime(final int number) {
        final var sqrt = (int) Math.sqrt(number);
        return IntStream.rangeClosed(2, sqrt).noneMatch(i -> number % i == 0);
    }
}
