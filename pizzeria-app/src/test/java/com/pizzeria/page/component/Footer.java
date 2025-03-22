package com.pizzeria.page.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class Footer {
    private final WebDriver driver;

    @FindBy(xpath = "//a[text()='Facebook']")
    private WebElement facebookButton;
    @FindBy(xpath = "//a[text()='ВКонтакте']")
    private WebElement vkontakteButton;
    @FindBy(xpath = "//a[text()='Instagram']")
    private WebElement instagramButton;

    public Footer(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
