package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.nio.file.Paths;

import static org.example.config.ConfigProvider.URL;

//https://playwright.dev/java/docs/trace-viewer-intro
//mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
public abstract class BasePage {
    private Playwright playwright;
    private Browser browser;
    protected Page page;
    private BrowserContext context;

    @BeforeSuite
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterSuite
    public void closeBrowser() {
        playwright.close();
    }

    @BeforeTest
    public void setUp() {
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
        page.navigate(URL);
    }

    @AfterTest
    public void stop() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        context.close();
    }
}
