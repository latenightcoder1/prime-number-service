package com.natwest.bank.api.service;

import com.natwest.bank.api.dto.PrimeResult;

/**
 * Prime number service interface permitting only PrimeNumberServiceImpl.
 */
public sealed interface PrimeNumberService permits PrimeNumberServiceImpl {


    PrimeResult getPrimesUpTo(final int number, final String algorithm, final String mediaType,
        final String sort);
}
