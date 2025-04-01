package com.interact.page;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class BasePage<T extends BasePage<T>> {
    @Setter
    protected static WebDriver driver;
    protected final Actions actions;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;

    public BasePage() {
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;
    }
}

