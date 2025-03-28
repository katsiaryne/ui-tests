package com.pizzeria;

import com.pizzeria.page.base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

abstract public class BaseTest {
    protected WebDriver webDriver;
    protected SoftAssert softAssert;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        softAssert = new SoftAssert();
        BasePage.setDriver(webDriver);
    }

    @AfterEach
    public void tearDown(){
        webDriver.close();
        webDriver.quit();
    }
}
