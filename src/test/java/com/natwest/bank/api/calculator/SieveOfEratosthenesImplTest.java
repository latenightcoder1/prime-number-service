package com.natwest.bank.api.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Prime calculator using Sieve of Eratosthenes unit tests")
class SieveOfEratosthenesImplTest {

    public static Stream<Arguments> inputOutputProvider() {
        return Stream.of(
            Arguments.of(10, List.of(2, 3, 5, 7)),
            Arguments.of(4, List.of(2, 3)),
            Arguments.of(5, List.of(2, 3, 5)),
            Arguments.of(100, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                53, 59, 61, 67, 71, 73, 79, 83, 89, 97)),
            Arguments.of(101, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101)),
            Arguments.of(2, List.of(2)),
            Arguments.of(1, List.of()),
            Arguments.of(-1, List.of()));
    }

    @ParameterizedTest
    @MethodSource("inputOutputProvider")
    @DisplayName("populated primes up to given number")
    void testGetPrimesUpTo(final int number, final List<Integer> primes) {
        final var result = new SieveOfEratosthenesImpl().getPrimesUpTo(number);
        assertEquals(primes, result, "Expected " + primes + " but actual was " + result);
    }

}
