package com.notes.page;

import com.codeborne.selenide.SelenideElement;
import com.notes.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class NotePage extends BasePage<NotePage> {
    private final SelenideElement editButton = $(By.xpath("//button[@class='pageArticle__button'][1]"));
    private final SelenideElement deleteButton = $(By.xpath("//button[@class='pageArticle__button'][2]"));
    private final SelenideElement titleField = $(".baseInput__field");
    private final SelenideElement textField = $(".baseTextarea__text");
    private final SelenideElement saveChangesButton = $(By.xpath("//button[@class='baseButton pageArticle__baseButton']"));
    private final SelenideElement declineChangesButton = $(By.xpath("//button[@class='baseButton pageArticle__baseButton baseButton--fillWhite']"));

    public NotePage cleatTextAndSetText(String text) {
        return clearFieldAndSetText(textField, text);
    }

    public NotePage clearTitleAndSetTitle(String title) {
        return clearFieldAndSetText(titleField, title);
    }

    public NotePage deleteNote() {
        return clickButton(deleteButton);
    }

    public NotePage editNote() {
        return clickButton(editButton);
    }

    public NotePage saveChanges() {
        return clickButton(saveChangesButton);
    }

    public NotePage declineChanges() {
        return clickButton(declineChangesButton);
    }
}
