package com.natwest.bank.api.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Prime calculator factory unit test cases")
public class PrimeAlgorithmFactoryTest {

    private PrimeAlgorithmFactory primeAlgorithmFactory;

    @Mock
    private SieveOfEratosthenesImpl sieveOfEratosthenes;

    @Mock
    private TrialDivisionImpl trialDivision;

    @BeforeEach
    public void setUp() {
        primeAlgorithmFactory = new PrimeAlgorithmFactory(trialDivision, sieveOfEratosthenes);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"sieve", "SIEVE", "Sieve", "sIEVE", "SIeve"})
    @DisplayName("Get Sieve Of Eratosthenes Prime calculator")
    void shouldGetSieveOfEratosthenes_whenSievePassed(final String algorithm) {
        assertEquals(sieveOfEratosthenes,
            primeAlgorithmFactory.getPrimeCalculationAlgorithm(algorithm));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"trial", "TRIAL", "Trial", "tRIAL", "TRial"})
    @DisplayName("Get Trial Division Prime calculator")
    void shouldGetTrialDivision_whenTrialPassed(final String algorithm) {
        assertEquals(trialDivision, primeAlgorithmFactory.getPrimeCalculationAlgorithm(algorithm));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"ABC", "XYZ"})
    @NullSource
    @EmptySource
    @DisplayName("Get no prime calculator if other than sieve/trial is passed")
    void shouldGetNull_whenUnKnowPassed(final String algorithm) {
        assertNull(primeAlgorithmFactory.getPrimeCalculationAlgorithm(algorithm));
    }

}
