package com.notes;

import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestDatePicker extends BaseTest {
    @DisplayName("Проверка установки даты")
    @ParameterizedTest
    @CsvSource(value = {
            "11, 11, 2024, '11 декабря 2024', 'Декабрь 2024'",
            "1, 1, 2025, '1 февраля 2025', 'Февраль 2025'"
    })
    public void testSetDatePicker(String day, String month, String year, String date, String monthAndYear) {
        StaticsPage page = new MainPage()
                .navigateToStatisticsPage()
                .setDate(day, month, year);
        assertAll(
                "Проверка изменения элементов страницы с изменением даты",
                () -> page.getPageStatisticDate().shouldBe(visible).shouldHave(text(date)),
                () -> page.getCalendarMonthYear().shouldBe(visible).shouldHave(text(monthAndYear))
        );
    }
}
