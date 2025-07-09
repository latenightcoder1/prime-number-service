package com.natwest.bank.api.controller.v1;

import static com.natwest.bank.api.constants.RequestParameters.ALGORITHM;
import static com.natwest.bank.api.constants.RequestParameters.PARAM_MEDIA_TYPE;
import static com.natwest.bank.api.constants.RequestParameters.SORT;

import com.natwest.bank.api.dto.PrimeApiResponse;
import com.natwest.bank.api.dto.PrimeResult;
import com.natwest.bank.api.service.PrimeNumberService;
import com.natwest.bank.api.utils.MediaTypeUtils;
import com.natwest.bank.api.utils.PrimeAlgorithmUtils;
import com.natwest.bank.api.utils.SortOrderUtils;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller holding client exposed apis.
 */
@RestController
@RequestMapping("v1/primes")
@Slf4j
@Validated //Enables validation on method params
public class PrimeNumberController {

    /**
     * {@link PrimeNumberService} service reference
     */
    private final PrimeNumberService primeNumberService;

    public PrimeNumberController(final PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }


    /**
     * Api to get all prime numbers up to a number.
     *
     * @param number    {@link Long} path param
     * @param mediaType {@link String} media type
     * @param algorithm {@link String} algorithm
     * @param sort      {@link String} query param
     * @return {@link List} of {@link Integer}
     */
    @GetMapping(value = "/{number}", produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public PrimeApiResponse getPrimesUpTo(
        @PathVariable @Min(value = 2, message = "number=${validatedValue} is less than {value}")
        @Max(value = 2147483646, message = "number=${validatedValue} is greater than {value}") final long number,
        @RequestParam(value = PARAM_MEDIA_TYPE, required = false) String mediaType,
        @RequestParam(name = ALGORITHM, required = false) String algorithm,
        @RequestParam(name = SORT, required = false) String sort) {
        log.info(
            "Received request to fetch all primes up to \"{}\" in \"{}\" order in \"{}\" format using algorithm \"{}\"",
            number, sort, mediaType, algorithm);
        mediaType = MediaTypeUtils.resolveMediaType(mediaType);
        algorithm = PrimeAlgorithmUtils.resolveAlgorithm(algorithm);
        sort = SortOrderUtils.resolveOrder(sort);
        log.info(
            "Received being processed to fetch all primes up to \"{}\" in \"{}\" order in \"{}\" format using algorithm \"{}\"",
            number, sort, mediaType, algorithm);
        final PrimeResult primeResult = primeNumberService.getPrimesUpTo((int) number, algorithm,
            mediaType, sort);
        return PrimeApiResponse.success(primeResult);
    }


}
