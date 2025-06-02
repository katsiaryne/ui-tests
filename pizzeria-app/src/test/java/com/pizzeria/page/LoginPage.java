package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage extends BasePage<LoginPage> {
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    public LoginPage setUsernameField(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public AccountInfoPage login() {
        loginButton.click();
        return new AccountInfoPage();
    }
}
