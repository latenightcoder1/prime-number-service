package com.natwest.bank.api.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.bank.api.PrimeNumberServiceApplication;
import com.natwest.bank.api.dto.PrimeApiResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * Class defining all bdd steps.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = PrimeNumberServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    private String rawResponse;
    private PrimeApiResponse jsonResponse;

    private String url;

    @Given("the prime number service is running")
    public void thePrimeNumberServiceIsRunning() {
        //a placeholder
    }

    @Given("I request primes up to {long}")
    public void i_request_primes_up_to(final long number) {
        url = "/v1/primes/" + number;
    }

    @Given("Using algorithm {string}")
    public void usingAlgorithm(final String algorithm) {
        final String algorithmAddition = "algorithm=" + algorithm;
        url = url.contains("?") ? (url + "&" + algorithmAddition) : (url + "?" + algorithmAddition);
    }

    @Given("in order {string}")
    public void inOrder(final String sort) {
        final String sortAddition = "sort=" + sort;
        url = url.contains("?") ? (url + "&" + sortAddition) : (url + "?" + sortAddition);
    }

    @When("with requested media type {string}")
    public void withRequestedMediaType(String mediaType) {
        final String mediaTypeAddition = "mediaType=" + mediaType;
        url = url.contains("?") ? (url + "&" + mediaTypeAddition) : (url + "?" + mediaTypeAddition);
    }

    @When("I send the request to the service")
    public void iSendTheRequestToTheService() {
        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
            String.class);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(final int statusCode) {
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @Then("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(final String mediaType)
        throws JsonProcessingException {
        assertThat(response.getHeaders().getContentType().toString()).startsWith(mediaType);
        rawResponse = response.getBody();
        if (mediaType.startsWith("application/json")) {
            jsonResponse = new ObjectMapper().readValue(rawResponse, PrimeApiResponse.class);
        }
    }

    @Then("the json response should have primes {string}")
    public void theJsonResponseShouldHavePrimes(String primes) {
        final List<Integer> expectedPrimes = Stream.of(primes.split(",")).map(String::trim)
            .map(Integer::valueOf).toList();
        assertThat(jsonResponse.data().primes()).isEqualTo(expectedPrimes);
    }

    @Then("the json response should not contain error")
    public void theJsonResponseShouldNotContainError() {
        assertThat(jsonResponse.error()).isNull();
    }

    @Then("the json response should show total time taken by request")
    public void theJsonResponseShouldShowTotalTimeTakenByRequest() {
        assertThat(jsonResponse.data().timeTakenMs()).isGreaterThan(-1);
    }

    @Then("the json response should be prepared with primes using {string} algorithm")
    public void theJsonResponseShouldBePreparedUsingAlgorithm(final String algorithm) {
        assertThat(jsonResponse.data().algorithm()).isEqualTo(algorithm);
    }

    @Then("the json response should have {string} order")
    public void theJsonResponseShouldHaveOrder(final String sort) {
        assertThat(jsonResponse.data().sort()).isEqualTo(sort);
    }

    @Then("the json response should contain error")
    public void theJsonResponseShouldContainError() {
        assertThat(jsonResponse.error()).isNotNull();
    }

    @Then("the json response should not contain data")
    public void theJsonResponseShouldNotContainData() {
        assertThat(jsonResponse.data()).isNull();
    }

    @Then("the json response should contain {string}")
    public void theJsonResponseShouldContain(final String message) {
        assertThat(jsonResponse.error().message()).contains(message);
    }

    @Then("the xml response should not contain error")
    public void theXmlResponseShouldNotContainError() {
        assertThat(rawResponse).contains("<error/>");
    }

    @Then("the xml response should show total time taken by request")
    public void theXmlResponseShouldShowTotalTimeTakenByRequest() {
        assertThat(rawResponse).doesNotContain("<timeTakenMs/>");
    }

    @Then("the xml response should have primes {string}")
    public void theXmlResponseShouldHavePrimes(final String xmlContent) {
        assertThat(rawResponse).contains(xmlContent);
    }

    @Then("the xml response should be prepared with primes using {string} algorithm")
    public void theXmlResponseShouldBePreparedUsingAlgorithm(final String algorithm) {
        final String algorithmMapping = "<algorithm>" + algorithm + "</algorithm>";
        assertThat(rawResponse).contains(algorithmMapping);
    }

    @Then("the xml response should have {string} order")
    public void theXmlResponseShouldHaveOrder(final String sort) {
        final String sortMapping = "<sort>" + sort + "</sort>";
        assertThat(rawResponse).contains(sortMapping);
    }

    @Then("the xml response should contain error")
    public void theXmlResponseShouldContainError() {
        assertThat(rawResponse).doesNotContain("<error/>");
    }

    @Then("the xml response should not contain data")
    public void theXmlResponseShouldNotContainData() {
        assertThat(rawResponse).contains("<data/>");
    }

    @Then("the xml response should contain {string}")
    public void theXmlResponseShouldContain(final String message) {
        assertThat(rawResponse).contains(message);
    }

}

