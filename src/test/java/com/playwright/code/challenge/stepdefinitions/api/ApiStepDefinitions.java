package com.playwright.code.challenge.stepdefinitions.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.playwright.code.challenge.api.endpoint.Endpoint;
import com.playwright.code.challenge.utils.JsonHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ApiStepDefinitions {

    private JsonHandler jsonHandler;

    @Autowired
    private Endpoint endpoint;

    private APIResponse apiResponse;

    @Given("a GET request is sent with id {long}")
    public void aGetRequestIsSentTo(long Id) {
        apiResponse = endpoint.getTask(Id);
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertThat(apiResponse.status(), is(statusCode));
    }

    @Then("the response body is equal to")
    public void theResponseBodyIsEqualTo(String body) {
        assertThat(apiResponse.body(), is(body));
    }

    public JsonNode getResponseBody() {
        return jsonHandler.read(apiResponse.body().asString());
    }
}
