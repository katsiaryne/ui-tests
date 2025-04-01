package com.name.parrot.core;

import com.name.parrot.page.MainPage;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T> {
    @Setter
    protected static WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public MainPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return new MainPage();
    }
}
