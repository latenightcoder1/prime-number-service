package com.natwest.bank.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "PrimeResult")
public record PrimeResult(int number, List<Integer> primes, String mediaType, String algorithm,
                          String sort, long timeTakenMs) {

}

