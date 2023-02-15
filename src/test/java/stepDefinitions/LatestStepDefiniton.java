package stepDefinitions;

import constants.Constants;
import constants.Messages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.TestUtils;

import java.util.Date;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class LatestStepDefiniton {

    private static Response response;
    RequestSpecification request = given();

    @Given("As an unauthorized user")
    public void as_an_unauthorized_user() {
        //Skipping setting the apikey header
    }

    @Given("As an user with invalid api key")
    public void as_an_user_with_invalid_api_key() {
        request = request.header("apikey", "INVALID");
    }

    @Given("As an authorized user")
    public void as_an_authorized_user() {
        request = request.header("apikey", Constants.API_KEY);
    }

    @When("I'm fetching the data with base {string} and symbols {string}")
    public void i_m_fetching_the_data_with_base_and_symbols(String base, String symbols) {
        response = request.when().get(Constants.LATEST_URL_FILTERED(symbols, base));
    }

    @When("I'm fetching the data without params")
    public void i_m_fetching_the_data_without_params() {
        response = request.when().get(Constants.LATEST_URL);
    }

    @When("I'm accessing non existing endpoint")
    public void i_m_accessing_non_existing_endpoint() {
        response = request.when().get(Constants.NON_EXISTING_URL);
    }

    @Then("The result is available")
    public void the_result_is_available() {
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().jsonPath().getBoolean("success"));
        Assert.assertEquals(response.getBody().jsonPath().getString("base"), "EUR");
        Assert.assertEquals(response.getBody().jsonPath().getString("date"), TestUtils.convertDate(new Date()));
    }

    @Then("The unauthorized error appears")
    public void the_unauthorized_error_appears() {
        Assert.assertEquals(response.getBody().jsonPath().getString("message"), Messages.NO_API_KEY);
    }

    @Then("The invalid authentication credentials message appears")
    public void the_invalid_authentication_credentials() {
        Assert.assertEquals(response.getBody().jsonPath().getString("message"), Messages.INVALID_AUTHENTICATION_CREDENTIALS);
    }

    @Then("Status code equals {int}")
    public void status_code_equals(Integer int1) {
        Assert.assertEquals(response.statusCode(), int1);
    }

    @Then("The result contains the rates converted from {string} to {string}")
    public void the_result_contains_the_rates_converted_from_to(String base, String symbols) {
        Assert.assertTrue(response.getBody().jsonPath().getBoolean("success"));
        Assert.assertEquals(response.getBody().jsonPath().getString("base"), base);
        Assert.assertEquals(response.getBody().jsonPath().getString("date"), TestUtils.convertDate(new Date()));
        LinkedHashMap ratesObject = response.getBody().jsonPath().getJsonObject("rates");
        Assert.assertEquals(ratesObject.size(), 3);
    }

    @Then("The not found message is present")
    public void the_not_found_message_is_present() {
        Assert.assertEquals(response.getBody().jsonPath().getString("message"), Messages.NO_ROUTE_MATCHED);
    }

    @Then("Body matches limited list response")
    public void body_matches_limited_list_response() {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(TestUtils.getSchemaByName("limited_response")));
    }

    @Then("Body matches full list response")
    public void body_matches_full_list_response() {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(TestUtils.getSchemaByName("full_response")));
    }

}
