package com.natwest.bank.api.utils;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("MediaTypeUtil unit test cases")
public class MediaTypeUtilsTest {

    public static Stream<Arguments> mediaTypeDataProvider() {
        return Stream.of(
            Arguments.of("xml", "xml"), Arguments.of("XML", "xml"),
            Arguments.of("xML", "xml"), Arguments.of("json", "json"),
            Arguments.of("XML1", "json"), Arguments.of("JSON", "json"),
            Arguments.of("", "json"), Arguments.of(null, "json"));
    }

    public static Stream<Arguments> responseMediaTypeDataProvider() {
        return Stream.of(
            Arguments.of("xml", "application/xml"), Arguments.of("XML", "application/xml"),
            Arguments.of("xML", "application/xml"), Arguments.of("json", "application/json"),
            Arguments.of("XML1", "application/json"), Arguments.of("JSON", "application/json"),
            Arguments.of("", "application/json"), Arguments.of(null, "application/json"));

    }

    @ParameterizedTest
    @DisplayName("Resolve media type to sanitized value")
    @MethodSource("mediaTypeDataProvider")
    void shouldResolveToKnownValue_whenMediaTypePassed(final String input, final String output) {
        Assertions.assertEquals(output, MediaTypeUtils.resolveMediaType(input));
    }

    @ParameterizedTest
    @DisplayName("Resolve response media type to sanitized value")
    @MethodSource("responseMediaTypeDataProvider")
    void shouldGetResponseMediaType_whenMediaTypePassed(final String input, final String output) {
        Assertions.assertEquals(output, MediaTypeUtils.getResponseMediaType(input));
    }

}
