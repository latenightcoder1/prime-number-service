package com.natwest.bank.api.utils;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("PrimeAlgorithmUtils unit test cases")
public class PrimeAlgorithmUtilsTest {

    public static Stream<Arguments> algorithmDataProvider() {
        return Stream.of(
            Arguments.of("sieve", "sieve"), Arguments.of("SIEVE", "sieve"),
            Arguments.of("sIEVE", "sieve"), Arguments.of("trial", "trial"),
            Arguments.of("TRIAL", "trial"), Arguments.of("trIAL", "trial"),
            Arguments.of("abc", null), Arguments.of("trialDivision", null),
            Arguments.of("", null), Arguments.of(null, null));
    }

    @ParameterizedTest
    @DisplayName("Resolve algorithm names to sanitized value")
    @MethodSource("algorithmDataProvider")
    void shouldResolveToKnownValue_whenAlgorithmPassed(final String input, final String output) {
        Assertions.assertEquals(output, PrimeAlgorithmUtils.resolveAlgorithm(input));
    }

}
