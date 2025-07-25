package com.natwest.bank.api.utils;

import static com.natwest.bank.api.constants.RequestParameters.ASCENDING;
import static com.natwest.bank.api.constants.RequestParameters.DESCENDING;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utils to process Sort order parameter.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SortOrderUtils {

    /**
     * Sanitize sort order.
     *
     * @param sort {@link String}
     * @return {@link String}
     */
    public static String resolveOrder(final String sort) {
        return DESCENDING.equalsIgnoreCase(sort) ? DESCENDING : ASCENDING;
    }
}
