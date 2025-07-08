package com.natwest.bank.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * As record has functions.
 */
@DisplayName("Prime Api Response unit test cases")
public class PrimeApiResponseTest {

    @Test
    @DisplayName("Prime Api response on success")
    public void shouldBuildPrimeApiResponse_whenPrimeResultProvided() {
        PrimeApiResponse response = PrimeApiResponse.success(
            new PrimeResult(2, List.of(2), "json", "sieve", "asc", 10L));
        assertNotNull(response.data());
        assertNull(response.error());
    }

    @Test
    @DisplayName("Prime Api response on error")
    public void shouldBuildPrimeApiResponse_whenErrorDetailsProvided() {
        PrimeApiResponse response = PrimeApiResponse.failure(
            new ErrorDetails(500, "Error in service"));
        assertNull(response.data());
        assertNotNull(response.error());
    }

}
