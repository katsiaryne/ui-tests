package com.interact;

import com.interact.page.notes.MainPageNotes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.interact.util.TestConstantsNote.FIRST_DEFAULT_NOTE_CONTENT;
import static com.interact.util.TestConstantsNote.SECOND_DEFAULT_NOTE_CONTENT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotebook extends BaseTest {
    @ParameterizedTest
    @DisplayName("Проверка модификации(добавление, удаление) записей")
    @CsvSource(value = "title, info, 10")
    public void mainFunctionalityCheck(String noteTitle, String noteInfo, int count) {
        MainPageNotes page = new MainPageNotes()
                .addNewNoteTimes(noteTitle, noteInfo, count)
                .deleteFirstNote()
                .scrollNotesContentWindowDown();
        List<WebElement> listOfVisibleNotes = page.getListOfVisibleNotes();
        int listSize = listOfVisibleNotes.size();
        assertAll(
                "Проверка наличия начальных записей",
                () -> assertEquals(FIRST_DEFAULT_NOTE_CONTENT, listOfVisibleNotes.get(listSize - 1).getText(), "Запись не соответствует ожидаемой"),
                () -> assertEquals(SECOND_DEFAULT_NOTE_CONTENT, listOfVisibleNotes.get(listSize - 2).getText(), "Запись не соответствует ожидаемой")
        );
    }
}
