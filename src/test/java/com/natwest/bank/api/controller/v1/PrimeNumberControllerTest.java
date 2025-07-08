package com.natwest.bank.api.controller.v1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.natwest.bank.api.dto.PrimeApiResponse;
import com.natwest.bank.api.dto.PrimeResult;
import com.natwest.bank.api.exception.PrimeServiceException;
import com.natwest.bank.api.service.PrimeNumberServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Prime number controller unit tests")
public class PrimeNumberControllerTest {

    private PrimeNumberController primeNumberController;

    @Mock
    private PrimeNumberServiceImpl primeNumberService;

    private static final List<Integer> PRIMES_UPTO_10 = List.of(2, 3, 5, 7);
    private static final String SIEVE = "sieve";
    private static final String ASC = "asc";
    private static final String JSON = "json";
    private static final int TEN = 10;

    @BeforeEach
    public void setUp() {
        primeNumberController = new PrimeNumberController(primeNumberService);
    }

    @Test
    @DisplayName("Controller returns PrimeApiResponse with data when service works")
    void shouldGetPrimeApiResponseWithData_whenServiceReturns() {

        final PrimeResult primeResult = new PrimeResult(TEN, PRIMES_UPTO_10, JSON, SIEVE, ASC,
            10L);
        when(primeNumberService.getPrimesUpTo(TEN, SIEVE, JSON, ASC)).thenReturn(primeResult);
        final PrimeApiResponse primeApiResponse = primeNumberController.getPrimesUpTo(TEN, JSON,
            SIEVE, ASC);
        assertNull(primeApiResponse.error());
        assertNotNull(primeApiResponse.data());
    }

    @Test
    @DisplayName("Controller throws exception if service throws exception")
    void shouldThrowException_whenServiceFails() {
        doThrow(new PrimeServiceException("runtime error")).when(primeNumberService)
            .getPrimesUpTo(TEN, SIEVE, JSON, ASC);
        final var exception = assertThrows(PrimeServiceException.class,
            () -> primeNumberController.getPrimesUpTo(TEN, JSON, SIEVE, ASC));
        Assertions.assertEquals("runtime error", exception.getMessage());
    }

}
