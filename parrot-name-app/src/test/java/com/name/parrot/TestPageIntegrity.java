package com.name.parrot;

import com.name.parrot.page.MainPage;
import com.name.parrot.page.frame.MainPageFooterFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPageIntegrity extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"email@email.email", "a@a.a"})
    @DisplayName("Проверка отображения хедера страницы")
    public void testHeaderIntegrity(String email) {
        WebElement header = new MainPage()
                .switchToFormFrame()
                .setEmail(email)
                .submitForm()
                .getHeader();
        assertAll(
                () -> assertTrue(header.isDisplayed(), "Заголовок не отображается")
        );
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке")
    public void testFormMessageIntegrity() {
        MainPageFooterFrame page = new MainPage()
                .switchToFormFrame()
                .submitForm()
                .switchToFooter()
                .openOfficialSite();
        closeLastOpenedWindowAndSwitchToPrevious();
        WebElement errorMessage = page
                .switchBackToFormFrame()
                .getEmailFormError();
        assertAll(
                () -> assertTrue(errorMessage.isDisplayed(), "Сообщение не отображается"),
                () -> assertEquals("Введите email", errorMessage.getText(), "Сообщение об ощибке неверное")
        );
    }

    @Test
    @DisplayName("Проверка отображения формы")
    public void testFormIntegrity() {
        MainPageFooterFrame page = new MainPage()
                .switchToFormFrame()
                .switchToFooter()
                .openOfficialSite();
        closeLastOpenedWindowAndSwitchToPrevious();
        WebElement form = page.switchBackToFormFrame().getForm();

        assertAll(
                () -> assertTrue(form.isDisplayed(), "Форма не отображается")
        );
    }
}
