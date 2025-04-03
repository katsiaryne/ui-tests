package com.interact;

import com.interact.page.feedback.MainPageFeedback;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.interact.util.FeedbackTestValidator.assertElementIsDisplayedAndHaveText;
import static com.interact.util.TestConstantsFeedback.SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://docs.google.com/spreadsheets/d/1R2i8tKGKz7aTY-iA_joQGGQzVeKP26g_4HpGNqzmy10/edit?usp=sharing
public class TestFeedback extends BaseTest {
    @ParameterizedTest
    @CsvSource(value = {
            "2025-04-11, 09:00, 18:00, 1234567890",
            "2025-04-07, 00:00, 23:00, 1234567890",
            "2025-04-16, 13:00, 23:00, 1234567890",
            "2025-04-17, 00:00, 13:00, 1234567890",
            "2025-04-15, 01:00, 22:00, 1234567890",
            "2025-04-28, 10:00, 10:00, 1234567890"
    })
    public void testValidFeedbackForm(String date, String timeFrom, String timeTo, String phone) {
        MainPageFeedback page = new MainPageFeedback()
                .setDate(date)
                .setTimeFrom(timeFrom)
                .setTimeTo(timeTo)
                .setPhone(phone)
                .submitForm();

        assertAll(
                "Проверка страницы подтверждения",
                () -> assertTrue(assertElementIsDisplayedAndHaveText(page.getSuccessfulMessage(), SUCCESS_MESSAGE), "Переход на страницу не произошёл")
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2025-04-05, 18:00, 23:00, 1234, 'К сожалению, в это время мы отдыхаем, выберите другое время', 'К сожалению, в это время мы отдыхаем, выберите другое время', 'Поле имеет ошибочный формат'",
            "2025-04-06, 18:00, 09:00, '', 'К сожалению, в это время мы отдыхаем, выберите другое время', 'К сожалению, в это время мы отдыхаем, выберите другое время', 'Поле обязательно для заполнения'",
            "'', 00:00, 09:00, '', 'Поле обязательно для заполнения', 'К сожалению, в это время мы отдыхаем, выберите другое время', 'Поле обязательно для заполнения'",

    })
    public void testInvalidFullForm(String date, String timeFrom, String timeTo, String phone,
                                    String dateError, String timeError, String phoneError) {

        MainPageFeedback page = new MainPageFeedback()
                .setDate(date)
                .setTimeFrom(timeFrom)
                .setTimeTo(timeTo)
                .setPhone(phone)
                .submitForm();

        assertAll(
                "Проверка ошибок страницы формы",
                () -> assertTrue(assertElementIsDisplayedAndHaveText(page.getDateMessage(), dateError)),
                () -> assertTrue(assertElementIsDisplayedAndHaveText(page.getTimeMessage(), timeError)),
                () -> assertTrue(assertElementIsDisplayedAndHaveText(page.getPhoneMessage(), phoneError))
        );
    }
}
