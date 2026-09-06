package com.playwright.code.challenge.hooks;

import com.playwright.code.challenge.factory.DriverFactory;
import io.cucumber.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {

    @Autowired
    private DriverFactory driverFactory;

    @After("@ui-automation")
    public void afterScenario() {
        driverFactory.closeBrowserContext();
    }
}
