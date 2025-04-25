package com.interact;

import com.interact.page.notes.MainPageNotes;
import com.interact.page.notes.StatisticsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDatePicker extends BaseTest {
    @ParameterizedTest
    @DisplayName("Проверка отображения выбранной даты в календаре")
    @CsvSource(value = {
            "2024, 1, 15, '15 февраля 2024'",
            "2025, 4, 10, '10 мая 2025'",
            "2026, 9, 3, '3 октября 2026'"
    })
    public void setDate(String year, String month, String day, String fullDate) {
        StatisticsPage page = new MainPageNotes()
                .openStats()
                .setDate(year, month, day);
        assertAll("Проверка установки даты в статистике",
                () -> assertEquals(fullDate, page.getCurrentDate().getText(), "Дата не изменилась после установки")
        );
    }
}
