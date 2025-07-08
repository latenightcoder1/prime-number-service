package com.natwest.bank.api.utils;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("SortOrderUtils unit test cases")
public class SortOrderUtilsTest {

    public static Stream<Arguments> sortOrderDetailsProvider() {
        return Stream.of(
            Arguments.of("ASC", "asc"), Arguments.of("asc", "asc"),
            Arguments.of("Asc", "asc"), Arguments.of("ascending", "asc"),
            Arguments.of("DESC", "desc"), Arguments.of("desc", "desc"),
            Arguments.of("Desc", "desc"), Arguments.of("descending", "asc"),
            Arguments.of(null, "asc"), Arguments.of("", "asc"));
    }

    @ParameterizedTest
    @DisplayName("Resolve sort order to sanitized value")
    @MethodSource("sortOrderDetailsProvider")
    void shouldResolveToKnownValue_whenSortPassed(final String input, final String output) {
        Assertions.assertEquals(output, SortOrderUtils.resolveOrder(input));
    }

}
