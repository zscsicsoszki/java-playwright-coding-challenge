package com.playwright.code.challenge.stepdefinitions.login;

import com.playwright.code.challenge.pageobjects.login.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginStepDefinitions {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Autowired
    private LoginPage loginPage;

    @Given("user navigates to the login page")
    public void navigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("user enters the email {string}")
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @When("user enters the password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks on the login button")
    public void clickOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("the login page is loaded")
    public void theLoginPageIsLoaded() {
        loginPage.isLoginPageVisible();
    }

    @Then("the login button is enabled")
    public void theLoginButtonIsEnabled() {
        loginPage.isLoginButtonVisible();
    }

    @Then("the login button is disabled")
    public void theLoginButtonIsDisabled() {
        loginPage.isDisabledLoginButtonVisible();
    }

    // Dummy step for challenge, does not reflect actual behavior
    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        assertThat(loginPage.getCurrentUrl(), is(baseUrl));
    }
}
