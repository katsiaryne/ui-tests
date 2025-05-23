package org.example.helpDesk;

import org.example.core.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(css = "input[@wfd-id='id2'")
    private WebElement rememberMe;
    @FindBy(css = "input[@wfd-id='id3'")
    private WebElement submitButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public TicketsPage login(String usernameValue, String passwordValue) {
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
        return new TicketsPage();
    }
}
