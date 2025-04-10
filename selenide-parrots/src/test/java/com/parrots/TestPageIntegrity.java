package com.parrots;

import com.codeborne.selenide.SelenideElement;
import com.parrots.page.MainPage;
import com.parrots.page.MainPageFooterFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestPageIntegrity extends BaseTest {
    @ParameterizedTest
    @ValueSource(strings = {"email@email.email", "a@a.a"})
    @DisplayName("Проверка отображения хедера страницы")
    public void testHeaderIntegrity(String email) {
        SelenideElement header = new MainPage()
                .switchToContentFrame()
                .setEmail(email)
                .submitForm()
                .getHeader();
        assertAll(
                "Проверка видимости Header",
                () -> header.should(exist, visible)
        );
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке")
    public void testFormMessageIntegrity() {
        MainPageFooterFrame page = new MainPage()
                .switchToContentFrame()
                .submitForm()
                .switchToFooterFrame()
                .openOfficialSite();
        switchToNewWindowCloseItAndSwitchToPrevious();
        SelenideElement errorMessage = page
                .switchToContentFrame()
                .getFormError();

        assertAll(
                "Проверка отображения сообщения об ошибке почты",
                () -> errorMessage.shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Проверка отображения формы")
    public void testFormIntegrity() {
        MainPageFooterFrame page = new MainPage()
                .switchToFooterFrame()
                .openOfficialSite();
        switchToNewWindowCloseItAndSwitchToPrevious();
        SelenideElement form = page.switchToContentFrame().getForm();

        assertAll(
                "Проверка отображения формы",
                () -> form.shouldBe(exist, visible)
        );
    }
}
