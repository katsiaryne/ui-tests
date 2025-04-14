package com.notes;

import com.notes.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNoteModification extends BaseTest {
    @DisplayName("Проверка добавления новых записей")
    @ParameterizedTest
    @CsvSource(value = {
            "'', 'note text', '(Без темы)'",
            "title, 'note text', title"
    })
    public void testCreateNoteFromMainPage(String title, String text, String expectedTitle) {
        MainPage page = new MainPage()
                .setTitle(title)
                .setText(text)
                .submitNote();
        assertAll(
                "Проверка отображения новой записи в списке",
                () -> page.getFirstArticleTitle().shouldHave(text(expectedTitle)),
                () -> assertEquals(text, page.getFirstArticleText().getText())
        );
    }

    @DisplayName("Проверка добавления новых записей с новой страницы")
    @ParameterizedTest
    @CsvSource(value = {
            "'', 'note text', '(Без темы)'",
            "title, 'note text', title"
    })
    public void testCreateNoteFromCreatePage(String title, String text, String expectedTitle) {
        MainPage page = new MainPage()
                .openCreatePage()
                .setTitle(title)
                .setText(text)
                .submitNote();
        assertAll(
                "Проверка отображения новой записи в списке",
                () -> page.getFirstArticleTitle().shouldHave(text(expectedTitle)),
                () -> assertEquals(text, page.getFirstArticleText().getText())
        );
    }

    @DisplayName("Проверка целостности записей")
    @Test
    public void testNoteIntegrity() {
        MainPage page = new MainPage()
                .createNotes(10)
                .deleteFirstArticle()
                .scrollNotesContent();
        assertAll(
                "",
                () -> assertEquals(11, page.getArticles().size())
        );
    }
}
