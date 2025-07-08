package com.natwest.bank.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestParameters {

    /**
     * Media type related constants.
     */
    public static final String HEADER_ACCEPT = "Accept";
    public static final String PARAM_MEDIA_TYPE = "mediaType";
    public static final String MEDIA_TYPE_XML = "xml";
    public static final String MEDIA_TYPE_JSON = "json";


    /**
     * Prime number algorithm related constants.
     */
    public static final String ALGORITHM = "algorithm";
    public static final String SIEVE_OF_ERATOSTHENES = "sieve";
    public static final String TRIAL_DIVISION = "trial";


    /**
     * Sorting related constants.
     */
    public static final String SORT = "sort";
    public static final String ASCENDING = "asc";
    public static final String DESCENDING = "desc";
}
