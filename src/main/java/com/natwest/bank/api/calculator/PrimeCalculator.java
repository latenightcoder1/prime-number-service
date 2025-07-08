package com.natwest.bank.api.calculator;

import java.util.List;

/**
 * Prime number calculator interface permitting only PrimeCalculatorImpl.
 */
public sealed interface PrimeCalculator permits TrialDivisionImpl, SieveOfEratosthenesImpl {


    List<Integer> getPrimesUpTo(final int number);
}
