Feature: check the data of /latest endpoint

  Scenario: Non existing endpoint - authorized user
    Given As an authorized user
    When I'm accessing non existing endpoint
    Then The not found message is present
    And Status code equals 404

  Scenario: Non existing endpoint - not authorized user
    Given As an authorized user
    When I'm accessing non existing endpoint
    Then The not found message is present
    And Status code equals 404

  Scenario: Unauthorized user
    Given As an unauthorized user
    When I'm fetching the data without params
    Then The unauthorized error appears
    And Status code equals 401

  Scenario: Invalid api key
    Given As an user with invalid api key
    When I'm fetching the data without params
    Then The invalid authentication credentials message appears
    And Status code equals 401

  Scenario: Authorized user - request without params
    Given As an authorized user
    When I'm fetching the data without params
    Then The result is available
    And Status code equals 200
    And Body matches full list response

  Scenario: Authorized user - request with params
    Given As an authorized user
    When I'm fetching the data with base "EUR" and symbols "GBP,JPY,USD"
    Then The result is available
    And Status code equals 200
    And The result contains the rates converted from "EUR" to "GBP,JPY,EUR"
    And Body matches limited list response




