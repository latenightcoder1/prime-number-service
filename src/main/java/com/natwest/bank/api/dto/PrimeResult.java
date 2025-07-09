package com.natwest.bank.api.dto;

import java.util.List;

public record PrimeResult(int number, List<Integer> primes, String mediaType, String algorithm,
                          String sort, long timeTakenMs) {

}

