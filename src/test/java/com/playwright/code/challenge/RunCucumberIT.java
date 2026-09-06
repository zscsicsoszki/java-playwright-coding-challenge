package com.playwright.code.challenge;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.playwright.code.challenge")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@ui-automation or @api-automation")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,"
        + "html:target/cucumber-pretty.html,"
        + "junit:target/cucumber-reports/cucumber.xml")
public class RunCucumberIT {
}
