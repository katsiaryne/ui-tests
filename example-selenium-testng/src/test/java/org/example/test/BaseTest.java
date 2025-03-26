package org.example.test;

import org.example.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.example.driver.DriverManager.quitDriver;

abstract public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
        quitDriver();
    }
}
