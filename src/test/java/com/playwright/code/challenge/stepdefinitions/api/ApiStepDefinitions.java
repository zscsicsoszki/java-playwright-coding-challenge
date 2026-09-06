package com.playwright.code.challenge.stepdefinitions.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.playwright.code.challenge.api.endpoint.Endpoint;
import com.playwright.code.challenge.utils.JsonHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ApiStepDefinitions {

    @Autowired
    private Endpoint endpoint;
    @Autowired
    private JsonHandler jsonHandler;

    @Getter
    private JsonNode requestPayload;
    private APIResponse response;
    private APIRequestContext requestContext;

    @Given("a request payload as")
    public void aRequestPayloadAs(JsonNode requestPayload) {
        this.requestPayload = requestPayload;
    }

    @Given("a request payload from {string}")
    public void aRequestPayloadFrom(String payloadLocation) {
        this.requestPayload = jsonHandler.readRequestPayload(payloadLocation);
    }

    @When("a GET request is sent with id {long}")
    public void aGetRequestIsSentTo(long id) {
        response = endpoint.getTask(id);
    }

    @When("a POST request is sent")
    public void aPostRequestIsSent() {
        response = endpoint.createTask(requestPayload);
    }

    @When("a PUT request is sent with id {long}")
    public void aPutRequestIsSentTo(long id) {
        response = endpoint.updateTask(id, requestPayload);
    }

    @When("a DELETE request is sent with id {long}")
    public void aDeleteRequestIsSentTo(long id) {
        response = endpoint.deleteTask(id);
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertThat(response.status(), is(statusCode));
    }

    @Then("the response body is equal to")
    public void theResponseBodyIsEqualTo(JsonNode responseBody) {
        assertThat(getResponseBody(), is(responseBody));
    }

    @Then("the response body should be equal to {string}")
    public void theResponseBodyShouldEqualTo(String responseLocation) {
        JsonNode expectedResponse = jsonHandler.readResponsePayload(responseLocation);
        assertThat(getResponseBody(), is(expectedResponse));
    }

    public JsonNode getResponseBody() {
        return jsonHandler.read(response);
    }
}
