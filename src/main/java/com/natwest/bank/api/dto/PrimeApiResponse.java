package com.natwest.bank.api.dto;

public record PrimeApiResponse(
    PrimeResult data,
    ErrorDetails error
) {

    /**
     * Build PrimeApiResponse on success.
     *
     * @param data {@link PrimeResult}
     * @return {@link PrimeApiResponse}
     */
    public static PrimeApiResponse success(final PrimeResult data) {
        return new PrimeApiResponse(data, null);
    }

    /**
     * Build PrimeApiResponse on error.
     *
     * @param error {@link ErrorDetails}
     * @return {@link PrimeApiResponse}
     */
    public static PrimeApiResponse failure(final ErrorDetails error) {
        return new PrimeApiResponse(null, error);
    }
}
