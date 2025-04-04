package com.interact;

import com.interact.page.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class WebinarBaseTest {
    protected WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(new ChromeOptions().addArguments("--window-size=720,1440"));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        BasePage.setDriver(webDriver);
    }

    @AfterEach
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }
}
