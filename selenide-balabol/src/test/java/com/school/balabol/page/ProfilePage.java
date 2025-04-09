package com.school.balabol.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class ProfilePage {
    @FindBy(xpath = "(//span[@role='combobox'])[1]")
    private SelenideElement selectLanguage;
    @FindBy(name = "name")
    private SelenideElement nameField;
    @FindBy(name = "last__name")
    private SelenideElement lastNameField;
    @FindBy(xpath = "(//span[@role='combobox'])[2]")
    private SelenideElement selectBirthYear;
    @FindBy(name = "location")
    private SelenideElement locationField;
    @FindBy(name = "comment")
    private SelenideElement commentField;
    @FindBy(name = "phone")
    private SelenideElement phoneField;
    @FindBy(name = "email")
    private SelenideElement emailField;
    @FindBy(name = "facebook")
    private SelenideElement facebookField;
    @FindBy(className = "button__submit")
    private SelenideElement submitFormButton;
    @FindBy(xpath = "//input[@id='uploadInput']")
    private SelenideElement uploadFileInput;


    public ProfilePage uploadAvatar() {
        executeJavaScript("document.getElementsByName(\"file\")[0].style.setProperty(\"width\",\"200px\");" +
                "document.getElementsByName(\"file\")[0].style.setProperty(\"height\",\"100px\");" +
                "document.getElementsByName(\"file\")[0].style.setProperty(\"opacity\",\"1\");" +
                "document.getElementsByName(\"file\")[0].style.setProperty(\"z-index\",\"1\");");
        $(".input-file").should(visible).uploadFile(new File("src/test/resources/avatar.jpg"));
        return this;
    }

    public ProfilePage selectCommunication(String[] communications) {
        for (String communication : communications) {
            $$(".ch_3 input").find(value(communication)).parent().click();
        }
        return this;
    }

    public ProfilePage selectNativeLanguage(String[] languages) {
        for (String language : languages) {
            $$(".ch_2 input").find(value(language)).parent().click();
        }
        return this;
    }

    public ProfilePage selectTeachLanguage(String language) {
        $(".ch_1 input").shouldHave(value(language)).parent().click();
        return this;
    }

    public ProfilePage selectAge(String year) {
        $(".select_age").selectOptionByValue(year);
        return this;
    }

    public ProfilePage selectLanguage(String language) {
        $(".select_language").selectOptionByValue(language);
        return this;
    }
}
