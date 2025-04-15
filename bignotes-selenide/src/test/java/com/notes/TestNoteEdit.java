package com.notes;

import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNoteEdit extends BaseTest {
    @ParameterizedTest
    @CsvSource(value = {
            "'new title', 'new text', 'new title'",
            "'', 'new text', '(Без темы)'"
    })
    public void testCreateNoteAndEdit(String title, String text, String expectedTitle) {
        StaticsPage page = new MainPage()
                .createNotes(1)
                .navigateToStatisticsPage()
                .openNote(1)
                .editNote()
                .clearTitleAndSetTitle(title)
                .cleatTextAndSetText(text)
                .saveChanges()
                .navigateToStatisticsPage();
        assertAll(
                "Проверка обновления записи на странице статистики",
                () -> assertEquals(expectedTitle, page.getNoteTitle(1).getText()),
                () -> assertEquals(text, page.getNoteText(1).getText())
        );
    }
}
