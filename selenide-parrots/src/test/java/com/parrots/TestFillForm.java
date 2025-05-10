package com.parrots;

import com.parrots.page.MainPage;
import com.parrots.page.MainPageContentFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.dismiss;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFillForm extends BaseTest {
    @DisplayName("Заполнение формы корректными данными")
    @ParameterizedTest
    @CsvSource(value = {
            "male,   boy@boy.boy,    'Хорошо, мы пришлём имя для вашего мальчика на e-mail:'",
            "female, girl@girl.girl, 'Хорошо, мы пришлём имя для вашей девочки на e-mail:'"
    })
    public void fillFormAndSubmitSuccessfully(String gender, String email, String successMessage) {
        MainPageContentFrame page = new MainPage()
                .switchToContentFrame()
                .setEmail(email)
                .selectGender(gender)
                .submitForm();

        assertAll(
                "Проверка на сообщение об успехе",
                () -> page.getResultEmail().shouldHave(text(email)),
                () -> page.getResultText().shouldHave(text(successMessage))
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
                .switchToContentFrame()
                .setEmail(email)
                .submitForm();

        assertAll(
                "Прокерка сообщения об некорректном email",
                () -> page.getFormError().shouldHave(text(expectedErrorMessage))
        );
    }

    @DisplayName("Заполнение формы и попытка изменить email")
    @ParameterizedTest
    @CsvSource(value = {
            "male,   boy@boy.boy,    girl@girl.girl, 'E-mail успешно изменён на girl@girl.girl'",
            "female, girl@girl.girl, boy@boy.boy,    'E-mail успешно изменён на boy@boy.boy'",
            "male,   boy@boy.boy,    wrongemail,     'E-mail некорректный'",
            "female, girl@girl.girl, wrongemail,     'E-mail некорректный'"
    })
    public void fillFormTryAnotherEmailAndSubmit(String gender, String email, String newEmail, String expectedMessage) {
        new MainPage()
                .switchToContentFrame()
                .setEmail(email)
                .selectGender(gender)
                .submitForm()
                .clickTryAnotherEmail();
        Alert alert = sendEmailToAlertAndConfirm(newEmail);
        assertAll(
                "Проверка сообщения из оповещания о смене email",
                () -> assertEquals(expectedMessage, alert.getText(), "Сообщение из оповещания неверное")
        );
        confirm();
    }

    @DisplayName("Отмена изменения поля email в оповещании")
    @ParameterizedTest
    @ValueSource(strings = "email@email.email")
    public void fillFormTryAnotherEmailAndDismissPromtAlert(String email) {
        MainPageContentFrame page = new MainPage()
                .switchToContentFrame()
                .setEmail(email)
                .submitForm()
                .clickTryAnotherEmail();
        dismiss();
        assertAll(
                "Проверка отображения элементом обновленной формы",
                () -> page.getAnotherEmailButton().shouldNotBe(visible, clickable),
                () -> page.getResultEmail().shouldNotBe(visible)
        );
    }
}
