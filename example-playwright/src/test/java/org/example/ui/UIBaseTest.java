package org.example.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class UIBaseTest {
    private static Playwright playwright;
    private static Browser browser;
    protected Page page;
    private BrowserContext context;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void stop() {
        context.close();
    }
}
