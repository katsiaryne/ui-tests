package org.example.api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

import static org.example.config.ConfigProvider.API_KEY;
import static org.example.config.ConfigProvider.API_URL;

public abstract class ApiBaseTest {
    protected Playwright playwright;
    protected APIRequestContext context;

    @BeforeSuite
    public void setUp() {
        playwright = Playwright.create();
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key", API_KEY);
        context = playwright
                .request()
                .newContext(new APIRequest.NewContextOptions()
                        .setBaseURL(API_URL)
                        .setExtraHTTPHeaders(headers));
    }

    @AfterSuite
    public void afterAll() {
        if (context != null) {
            context.dispose();
            context = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
