package com.name.parrot.page.frame;

import com.name.parrot.core.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class MainPageContentFrame extends BasePage<MainPageContentFrame> {
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailFiled;
    @FindBy(id = "sendMe")
    private WebElement submitButton;
    @FindBy(id = "anotherEmail")
    private WebElement tryAnotherEmailButton;
    @FindBy(css = "#resultTextBlock .result-text")
    private WebElement resultMessage;
    @FindBy(css = "#resultTextBlock .your-email")
    private WebElement resultEmail;
    @FindBy(xpath = "//iframe[@scrolling='no']")
    private WebElement footerContentFrame;
    @FindBy(css = ".form-error")
    private WebElement emailFormError;
    @FindBy(xpath = "//div[@class='header']")
    private WebElement header;
    @FindBy(id = "form")
    private WebElement form;

    public MainPageContentFrame setEmail(String email) {
        emailFiled.sendKeys(email);
        return this;
    }

    public MainPageContentFrame setGender(String gender) {
        driver.findElement(By.id(gender)).click();
        return this;
    }

    public MainPageContentFrame submitForm() {
        submitButton.click();
        wait.until(ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(tryAnotherEmailButton),
                        ExpectedConditions.visibilityOf(emailFormError)
                )
        );
        return this;
    }

    public MainPageContentFrame clickTryAnotherEmail() {
        tryAnotherEmailButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        return this;
    }

    public MainPageFooterFrame switchToFooter() {
        driver.switchTo().frame(footerContentFrame);
        return new MainPageFooterFrame();
    }
}
