Feature: Prime number service Integration test cases.

  Background:
    Given the prime number service is running

  Scenario: Get prime numbers up to a given number for valid input
    Given I request primes up to 10
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/json"
    And the json response should have primes "2,3,5,7"
    And the json response should not contain error
    And the json response should show total time taken by request

  Scenario: Get prime numbers up to a given number using sieve algorithm
    Given I request primes up to 20
    And Using algorithm "sieve"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/json"
    And the json response should not contain error
    And the json response should have primes "2,3,5,7,11,13,17,19"
    And the json response should show total time taken by request
    And the json response should be prepared with primes using "sieve" algorithm

  Scenario: Get prime numbers up to a given number using trial division algorithm
    Given I request primes up to 20
    And Using algorithm "trial"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/json"
    And the json response should not contain error
    And the json response should have primes "2,3,5,7,11,13,17,19"
    And the json response should show total time taken by request
    And the json response should be prepared with primes using "trial" algorithm

  Scenario: Get prime numbers up to a given number in desc order in json format using trial division algorithm
    Given I request primes up to 20
    And Using algorithm "trial"
    And in order "desc"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/json"
    And the json response should not contain error
    And the json response should have primes "19,17,13,11,7,5,3,2"
    And the json response should show total time taken by request
    And the json response should be prepared with primes using "trial" algorithm
    And the json response should have "desc" order

  Scenario: Get prime numbers up to a given number in desc order in json format using sieve algorithm
    Given I request primes up to 20
    And Using algorithm "sieve"
    And in order "desc"
    And with requested media type "json"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/json"
    And the json response should not contain error
    And the json response should have primes "19,17,13,11,7,5,3,2"
    And the json response should show total time taken by request
    And the json response should be prepared with primes using "sieve" algorithm
    And the json response should have "desc" order

  Scenario: Get prime numbers up to a given number in json format for a less than 2
    Given I request primes up to 1
    And with requested media type "json"
    When I send the request to the service
    Then the response status should be 400
    And the response content type should be "application/json"
    And the json response should contain error
    And the json response should not contain data
    And the json response should contain "number=1 is less than 2"

  Scenario: Get prime numbers up to a given number in xml format
    Given I request primes up to 10
    And with requested media type "xml"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/xml"
    And the xml response should not contain error
    And the xml response should show total time taken by request
    And the xml response should have primes "<primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes></primes>"

  Scenario: Get prime numbers up to a given number in xml format using sieve algorithm
    Given I request primes up to 10
    And Using algorithm "sieve"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/xml"
    And the xml response should not contain error
    And the xml response should show total time taken by request
    And the xml response should have primes "<primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes></primes>"
    And the xml response should be prepared with primes using "sieve" algorithm

  Scenario: Get prime numbers up to a given number in desc order in xml format using trial division algorithm
    Given I request primes up to 10
    And Using algorithm "trial"
    And in order "desc"
    And with requested media type "xml"
    When I send the request to the service
    Then the response status should be 200
    And the response content type should be "application/xml"
    And the xml response should not contain error
    And the xml response should show total time taken by request
    And the xml response should have primes "<primes><primes>7</primes><primes>5</primes><primes>3</primes><primes>2</primes></primes>"
    And the xml response should be prepared with primes using "trial" algorithm
    And the xml response should have "desc" order

  Scenario: Get prime numbers up to a given number in xml format for a less than 2
    Given I request primes up to -5
    And with requested media type "xml"
    When I send the request to the service
    Then the response status should be 400
    And the response content type should be "application/xml"
    And the xml response should contain error
    And the xml response should not contain data
    And the xml response should contain "number=-5 is less than 2"