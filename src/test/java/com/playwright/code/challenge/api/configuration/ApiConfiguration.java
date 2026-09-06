package com.playwright.code.challenge.api.configuration;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ApiConfiguration {

    @Bean
    public APIRequestContext apiRequestContext() {
        Playwright playwright = Playwright.create();

        return playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://task.site/")
                        .setExtraHTTPHeaders(Map.of(
                                "Content-Type", "application/json",
                                "Accept", "application/json"
                        ))
        );
    }
}
