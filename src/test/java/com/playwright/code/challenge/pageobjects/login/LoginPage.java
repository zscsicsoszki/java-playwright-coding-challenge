package com.playwright.code.challenge.pageobjects.login;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.playwright.code.challenge.pageobjects.BasePageObject;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class LoginPage extends BasePageObject {

    private static final String LOGIN_PAGE_URL = "https://account.aldi.us/s/login/";

    protected final Locator emailInput;
    protected final Locator passwordInput;
    protected final Locator loginButton;
    protected final Locator disabledLoginButton;
    protected final Locator forgotPasswordLink;
    protected final Locator registerLink;
    protected final Locator loginErrorMessage;

    @Autowired
    public LoginPage(Page page) {
        super(page);

        emailInput = page.locator("input[name=\"email\"]");
        passwordInput = page.locator("input[name=\"passw\"]");
        loginButton = page.locator("button[aria-label='Log In']");
        disabledLoginButton = page.locator("button[aria-label=\"Log In\"][aria-disabled=true]");
        forgotPasswordLink = page.locator("a[aria-label=\"Forgot your password?\"]");
        registerLink = page.locator("a[aria-label=\"Don't have an account? Register here!\"]");
        loginErrorMessage = page.locator(".cus_login_fail_err span.reset-link");
    }

    // Actions + Clicking

    public void navigateToLoginPage() {
        navigateTo(LOGIN_PAGE_URL);
    }

    public void enterEmail(String email) {
        emailInput.fill(email);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void login(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
        loginButton.click();
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage.innerText();
    }

    // Visibility

    public void isEmailInputFieldVisible() {
        emailInput.isVisible();
    }

    public void isPasswordInputFieldVisible() {
        passwordInput.isVisible();
    }

    public void isLoginButtonVisible() {
        loginButton.isVisible();
    }

    public void isDisabledLoginButtonVisible() {
        disabledLoginButton.isVisible();
    }

    public void isForgotPasswordLinkVisible() {
        forgotPasswordLink.isVisible();
    }

    public void isRegisterLinkVisible() {
        registerLink.isVisible();
    }

    public void isLoginErrorMessageVisible() {
        loginErrorMessage.isVisible();
    }

    public void isLoginPageVisible() {
        isEmailInputFieldVisible();
        isPasswordInputFieldVisible();
        isDisabledLoginButtonVisible();
        isForgotPasswordLinkVisible();
        isRegisterLinkVisible();
    }

}
