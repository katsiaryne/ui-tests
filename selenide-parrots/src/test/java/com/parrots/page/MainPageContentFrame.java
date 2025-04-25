package com.parrots.page;

import com.codeborne.selenide.SelenideElement;
import com.parrots.page.base.BaseMainPage;
import lombok.Getter;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class MainPageContentFrame extends BaseMainPage<MainPageContentFrame> {
    private final SelenideElement submitButton = $("#sendMe");
    private final SelenideElement genderRadioButtons = $(".parrot input");
    private final SelenideElement emailField = $(byName("email"));
    private final SelenideElement formError = $(".form-error");
    private final SelenideElement resultText = $(".result-text");
    private final SelenideElement resultEmail = $("#resultTextBlock pre");
    private final SelenideElement anotherEmailButton = $("#anotherEmail");
    private final SelenideElement header = $(".header");
    private final SelenideElement form = $(".main-form");

    public MainPageContentFrame setEmail(String email) {
        emailField.should(visible).click();
        emailField.should(interactable).sendKeys(email);
        return this;
    }

    public MainPageContentFrame submitForm() {
        submitButton.should(visible).click();
        return this;
    }

    public MainPageContentFrame selectGender(String gender) {
        genderRadioButtons.selectRadio(gender).click();
        return this;
    }

    public MainPageContentFrame clickTryAnotherEmail() {
        anotherEmailButton.should(visible).click();
        return this;
    }
}
