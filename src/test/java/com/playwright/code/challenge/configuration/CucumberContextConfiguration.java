package com.playwright.code.challenge.configuration;

import org.springframework.test.context.ContextConfiguration;

@io.cucumber.spring.CucumberContextConfiguration
@ContextConfiguration(classes = SpringConfiguration.class)
public class CucumberContextConfiguration {
}
