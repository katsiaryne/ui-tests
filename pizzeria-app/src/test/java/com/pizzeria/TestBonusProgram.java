package com.pizzeria;

import com.pizzeria.page.BonusProgramPage;
import com.pizzeria.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.pizzeria.util.TabActionUtils.acceptInfoAlertAndSwitchToWindow;
import static com.pizzeria.util.TestValues.EMAIL;
import static com.pizzeria.util.TestValues.PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка заведения бонусной карты")
public class TestBonusProgram extends BaseTest {

    @Test
    @DisplayName("Успешной оформление бонусной карты")
    public void activateBonusCard() {
        BonusProgramPage page = new MainPage()
                .getHeader()
                .openBonusPage()
                .setUsername(EMAIL)
                .setPhone(PHONE_NUMBER)
                .activateBonusCard();
        acceptInfoAlertAndSwitchToWindow(webDriver);

        assertAll(
                "Проверка сообщения об успехе",
                () -> assertEquals("Ваша карта оформлена!", page.getFormSuccessMessage().getText())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'', 'Поле \"Телефон\" обязательно для заполнения'",
            "qweqweqwe, 'Введен неверный формат телефона'"
    })
    @DisplayName("Ввод невалидных данных при оформлении карты")
    public void activateBonusCardWithIncorrectPhone(String phone, String expectedMessage) {
        BonusProgramPage page = new MainPage()
                .getHeader()
                .openBonusPage()
                .setUsername(EMAIL)
                .setPhone(phone)
                .activateBonusCard();
        assertAll(
                "Проверка сообщения об успехе",
                () -> assertEquals(expectedMessage, page.getFormErrorMessages().getText())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'', 'Поле \"Имя\" обязательно для заполнения'"
    })
    @DisplayName("Ввод невалидных данных при оформлении карты")
    public void activateBonusCardWithIncorrectUsername(String username, String expectedMessage) {
        BonusProgramPage page = new MainPage()
                .getHeader()
                .openBonusPage()
                .setUsername(username)
                .setPhone(PHONE_NUMBER)
                .activateBonusCard();
        assertAll(
                "Проверка сообщения об успехе",
                () -> assertEquals(expectedMessage, page.getFormErrorMessages().getText())
        );
    }
}
