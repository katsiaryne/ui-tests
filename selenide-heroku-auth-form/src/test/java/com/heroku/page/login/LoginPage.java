package com.heroku.page.login;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage {
    private final SelenideElement usernameField = $(By.name("username"));
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement submitFormButton = $("form button");
    private final SelenideElement formError = $(".flash.error");

    public LoginPage setUsername(String username) {
        return setField(usernameField, username);
    }

    public LoginPage setPassword(String password) {
        return setField(passwordField, password);
    }

    public LoginPage submitLoginFormError() {
        submitFormButton.shouldBe(clickable).click();
        return new LoginPage();
    }

    public SecureAreaPage submitLoginFormSuccess() {
        submitFormButton.shouldBe(clickable).click();
        return new SecureAreaPage();
    }

    private LoginPage setField(SelenideElement field, String value) {
        field.shouldBe(clickable).click();
        field.shouldBe(editable).sendKeys(value);
        return this;
    }
}
