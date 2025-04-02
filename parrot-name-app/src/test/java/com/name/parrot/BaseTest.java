package com.name.parrot;

import com.name.parrot.core.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        BasePage.setDriver(webDriver);
    }

    @AfterEach
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }

    public static void closeLastOpenedWindowAndSwitchToPrevious() {
        Object[] windowHandles = webDriver.getWindowHandles().toArray();
        int index = windowHandles.length - 1;
        webDriver.switchTo().window((String) windowHandles[index]).close();
        webDriver.switchTo().window((String) windowHandles[index - 1]);
    }

    public static Alert switchToPromtAlertAndSendEmail(String email) {
        Alert alert = webDriver.switchTo().alert();
        alert.sendKeys(email);
        alert.accept();
        return webDriver.switchTo().alert();
    }

    public static void switchToPromtAlertAndDismiss() {
        Alert alert = webDriver.switchTo().alert();
        alert.dismiss();
    }

    public static void closeInfoAlert(Alert alert) {
        alert.accept();
    }
}
