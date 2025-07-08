package com.natwest.bank.api.advice;

import com.natwest.bank.api.dto.ErrorDetails;
import com.natwest.bank.api.dto.PrimeApiResponse;
import com.natwest.bank.api.exception.PrimeServiceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller advice around exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Wrap ConstraintViolationException as a Bad Request.
     *
     * @param e {@link ConstraintViolationException}
     * @return {@link ResponseEntity} with body {@link PrimeApiResponse}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<PrimeApiResponse> handleValidationException(
        final ConstraintViolationException e) {
        final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
            "Validation error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(PrimeApiResponse.failure(errorDetails));
    }

    /**
     * Wrap PrimeServiceException as an Internal Server Error.
     *
     * @param e {@link PrimeServiceException}
     * @return {@link ResponseEntity} with body {@link PrimeApiResponse}
     */
    @ExceptionHandler(PrimeServiceException.class)
    public ResponseEntity<PrimeApiResponse> handleKnownException(final PrimeServiceException e) {
        final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Runtime Error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(PrimeApiResponse.failure(errorDetails));
    }

    /**
     * Wrap any other exception as an Internal Server Error.
     *
     * @param e {@link Exception}
     * @return {@link ResponseEntity} with body {@link PrimeApiResponse}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<PrimeApiResponse> handleUncaughtException(final Exception e) {
        final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(PrimeApiResponse.failure(errorDetails));
    }

}
