package com.playwright.code.challenge.configuration;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.playwright.code.challenge.factory.DriverFactory;
import com.playwright.code.challenge.utils.JsonHandler;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:configuration/test.properties")
@ComponentScan(basePackages = "com.playwright.code.challenge")
public class SpringConfiguration {

    @Bean(destroyMethod = "closeBrowser")
    public DriverFactory driverFactory() {
        return new DriverFactory();
    }

    @Bean
    @ScenarioScope
    public BrowserContext browserContext(DriverFactory driverFactory) {
        return driverFactory.createBrowserContext();
    }

    @Bean
    @ScenarioScope
    public Page page(BrowserContext browserContext) {
        return browserContext.newPage();
    }

    @Bean
    public JsonHandler jsonHandler() {
        return new JsonHandler();
    }
}
