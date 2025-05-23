package org.example.ui;

import org.example.page.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMainPageAddNote extends UIBaseTest {
    @ParameterizedTest
    @CsvSource({
            "title, text, title",
            "'', text, '(Без темы)'"
    })
    public void testAddNewNote(String title, String text, String expectedTitle) {
        MainPage resultPage = new MainPage(page)
                .setTitleField(title)
                .setTextField(text)
                .submitNote();

        assertAll(
                "Проверка добавления новой записи",
                () -> assertTrue(resultPage.getTextField().textContent().isEmpty()),
                () -> assertTrue(resultPage.getTextField().textContent().isEmpty()),
                () -> assertEquals(expectedTitle, resultPage.getFirstNoteTitle()),
                () -> assertEquals(text, resultPage.getFirstNoteText())
        );
    }

    @ParameterizedTest
    @CsvSource("title, text, title")
    public void testDeleteNewNote(String title, String text) {
        MainPage midPage = new MainPage(page)
                .setTitleField(title)
                .setTextField(text)
                .submitNote();
        int startSize = midPage.getNoteList().all().size();
        MainPage resultPage = midPage.deleteFirstNote();

        assertAll(
                "Проверка удаления новой записи",
                () -> assertEquals(1, startSize - resultPage.getNoteList().all().size())
        );
    }
}
