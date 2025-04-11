package com.heroku.page.login;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class SecureAreaPage {
    private final SelenideElement successLoginMessage = $(".flash.success");
    private final SelenideElement logoutButton = $(".button");

    public LoginPage logout() {
        logoutButton.shouldBe(clickable).click();
        return new LoginPage();
    }
}
