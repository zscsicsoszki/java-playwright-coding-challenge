package com.playwright.code.challenge.factory;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DriverFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    @Value("${browserName:chrome}")
    private String browserName;
    @Value("${headless:true}")
    private boolean headless;

    public void initializeBrowser() {
        playwright = Playwright.create();
        if (browser == null) {
            switch (browserName.toLowerCase()) {
                case "chromium":
                case "chrome":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                    log.info("Chrome Browser launched successfully");
                    break;
                case "firefox":
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                    log.info("Firefox Browser launched successfully");
                    break;
                case "webkit":
                case "safari":
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                    log.info("WebKit Browser launched successfully");
                    break;
                default:
                    throw new IllegalArgumentException("Please provide a valid browser name (chrome, firefox, webkit or chromium).");
            }
        }
    }

    public BrowserContext createBrowserContext() {
        initializeBrowser();
        browserContext = browser.newContext();
        return browserContext;
    }

    public void closeBrowserContext() {
        if (browserContext != null) {
            browserContext.close();
            browserContext = null;
        }
    }

    public void closeBrowser() {
        closeBrowserContext();

        if (browser != null) {
            browser.close();
            browser = null;
        }

        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
