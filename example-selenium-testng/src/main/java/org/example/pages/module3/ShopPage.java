package org.example.pages.module3;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.config.ConfigProvider.URL_SHOP_PAGE;

@Getter
public class ShopPage {
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='input-field']//input")
    private WebElement inputField;
    @FindBy(xpath = "//form[@class='check-size']//button")
    private WebElement submitSizeButton;
    @FindBy(xpath = "//div[@class='input-field']//label")
    private WebElement submitMessage;

    public ShopPage(WebDriver driver) {
        driver.get(URL_SHOP_PAGE);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShopPage setInputField(String size) {
        inputField.clear();
        inputField.sendKeys(size);
        return this;
    }

    public ShopPage submitSize() {
        submitSizeButton.click();
        return this;
    }
}
