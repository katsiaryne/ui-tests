package org.example.pages.module1;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.config.ConfigProvider.URL_HELLO_PAGE;

@Getter
public class HelloPage {
    private final WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Имя']")
    private WebElement nameField;
    @FindBy(xpath = "//button[text()='Ввод']")
    private WebElement submitButton;
    @FindBy(css = ".start-screen__res")
    private WebElement formMessage;

    public HelloPage(WebDriver driver) {
        driver.get(URL_HELLO_PAGE);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HelloPage submitName() {
        submitButton.click();
        return this;
    }

    public HelloPage setName(String name) {
        nameField.sendKeys(name);
        return this;
    }
}
