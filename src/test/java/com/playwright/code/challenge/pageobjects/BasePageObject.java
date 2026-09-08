package com.playwright.code.challenge.pageobjects;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasePageObject {
    protected Page page;

    public BasePageObject(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        log.info("Navigating to URL: {}", url);
        page.navigate(url);
    }

    public void refresh() {
        log.info("Refreshing page");
        page.reload();
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public void scrollToTop() {
        page.evaluate("window.scrollTo(0, 0)");
    }

    public void scrollToBottom() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }
}
