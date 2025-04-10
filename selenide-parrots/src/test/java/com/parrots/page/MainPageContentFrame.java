package com.parrots.page;

import com.codeborne.selenide.SelenideElement;
import com.parrots.page.base.BaseMainPage;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class MainPageContentFrame extends BaseMainPage<MainPageContentFrame> {
    private final SelenideElement submitButton = $("#sendMe");
    private final SelenideElement genderRadioButtons = $(".parrot input");
    private final SelenideElement emailField = $(By.name("email"));
    private final SelenideElement formError = $(".form-error");
    private final SelenideElement resultText = $(".result-text");
    private final SelenideElement resultEmailText = $("#resultTextBlock pre");
    private final SelenideElement anotherEmailButton = $("#anotherEmail");

    private MainPageContentFrame fillEmailField(String email) {
        emailField.should(clickable).click();
        emailField.should(interactable).sendKeys(email);
        return this;
    }

    public MainPageContentFrame submitForm() {
        submitButton.should(clickable).click();
        return this;
    }

    public MainPageContentFrame selectGender(String gender) {
        genderRadioButtons.selectRadio(gender).click();
        return this;
    }

    public MainPageContentFrame tryAnotherEmailButton() {
        anotherEmailButton.should(clickable).click();
        return this;
    }
}
