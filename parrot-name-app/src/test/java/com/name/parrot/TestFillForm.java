package com.name.parrot;

import com.name.parrot.page.MainPage;
import com.name.parrot.page.frame.MainPageContentFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFillForm extends BaseTest {
    @DisplayName("Заполнение формы корректными данными")
    @ParameterizedTest
    @CsvSource(value = {
            "boy,  boy@boy.boy,    'Хорошо, мы пришлём имя для вашего мальчика на e-mail:'",
            "girl, girl@girl.girl, 'Хорошо, мы пришлём имя для вашей девочки на e-mail:'"
    })
    public void fillFormAndSubmitSuccessfully(String gender, String email, String successMessage) {
        MainPageContentFrame page = new MainPage()
                .switchToFormFrame()
                .setEmail(email)
                .setGender(gender)
                .submitForm();

        assertAll(
                () -> assertEquals(email, page.getResultEmail().getText(), "Почта не соответствует ожидемой"),
                () -> assertEquals(successMessage, page.getResultMessage().getText(), "Сообщение об успехе неверное")
        );
    }

    @DisplayName("Заполнение формы невалидной почтой")
    @ParameterizedTest
    @CsvSource(value = {
            "'',    'Введите email'",
            "'a',   'Некорректный email'",
            "'a@a', 'Некорректный email'",
            "'.@',  'Некорректный email'",
            "'@.',  'Некорректный email'"
    })
    public void fillFormWithWrongEmail(String email, String expectedErrorMessage) {
        MainPageContentFrame page = new MainPage()
                .switchToFormFrame()
                .setEmail(email)
                .submitForm();

        assertAll(
                () -> assertEquals(expectedErrorMessage, page.getEmailFormError().getText(), "Сообщение об ошибке неверное")
        );
    }

    @DisplayName("Заполнение формы и попытка изменить email")
    @ParameterizedTest
    @CsvSource(value = {
            "boy,  boy@boy.boy,    girl@girl.girl, 'E-mail успешно изменён на girl@girl.girl'",
            "girl, girl@girl.girl, boy@boy.boy,    'E-mail успешно изменён на boy@boy.boy'",
            "boy,  boy@boy.boy,    wrongemail,     'E-mail некорректный'",
            "girl, girl@girl.girl, wrongemail,     'E-mail некорректный'"
    })
    public void fillFormTryAnotherEmailAndSubmit(String gender, String email, String newEmail, String expectedMessage) {
        new MainPage()
                .switchToFormFrame()
                .setEmail(email)
                .setGender(gender)
                .submitForm()
                .clickTryAnotherEmail();
        Alert alert = switchToPromtAlertAndSendEmail(newEmail);
        assertAll(
                () -> assertEquals(expectedMessage, alert.getText(), "Сообщение из оповещания неверное")
        );
        closeInfoAlert(alert);
    }

    @DisplayName("Отмена изменения поля email в оповещании")
    @ParameterizedTest
    @ValueSource(strings = "email@email.email")
    public void fillFormTryAnotherEmailAndDismissPromtAlert(String email) {
        MainPageContentFrame page = new MainPage()
                .switchToFormFrame()
                .setEmail(email)
                .submitForm()
                .clickTryAnotherEmail();
        switchToPromtAlertAndDismiss();

        assertAll(
                () -> assertTrue(page.getTryAnotherEmailButton().isDisplayed(), "Кнопка \"Указать другой email\" не отображается"),
                () -> assertTrue(page.getResultEmail().isDisplayed(), "Подтвержденная почта не отображается")
        );

    }
}
