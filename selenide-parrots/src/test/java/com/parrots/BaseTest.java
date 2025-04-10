package com.parrots;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.parrots.config.ConfigProvider.URL;

public abstract class BaseTest {
    @BeforeEach
    void setUp() {
        open(URL);
    }

    protected void switchToNewWindowCloseItAndSwitchToPrevious() {
        String currentWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        switchTo().window(WebDriverRunner.getWebDriver().getWindowHandles().size() - 1).close();
        switchTo().window(currentWindow);
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
