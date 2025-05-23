package org.example.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

import static org.example.config.ConfigProvider.NOTE_URL;

@Getter
public class MainPage extends BasePage {
    private final Locator titleField;
    private final Locator textField;
    private final Locator addNoteButton;
    private final Locator noteList;
    private final Locator noteTitle;
    private final Locator noteText;
    private final Locator noteDeleteButton;

    public MainPage(Page page) {
        super(page);
        page.navigate(NOTE_URL);
        titleField = page.locator(".baseInput__field");
        textField = page.locator(".baseTextarea__text");
        addNoteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Добавить заметку"));
        noteList = page.locator("//div[@class='articlePreview pageCreate__articlePreview']");
        noteTitle = page.locator(".articlePreview__title");
        noteText = page.locator(".articlePreview__text");
        noteDeleteButton = page.locator("(//button[@class='articlePreview__button'])[2]");
    }

    public MainPage setTitleField(String title) {
        titleField.fill(title);
        return this;
    }

    public MainPage setTextField(String text) {
        textField.fill(text);
        return this;
    }

    public MainPage submitNote() {
        addNoteButton.click();
        return this;
    }

    public String getFirstNoteTitle() {
        return noteList.first().locator(noteTitle).textContent();
    }

    public String getFirstNoteText() {
        return noteList.first().locator(noteText).textContent();
    }

    public MainPage deleteFirstNote() {
        noteList.first().hover();
        Locator deleteButton = noteList.locator(noteDeleteButton);
        deleteButton.click();
        return this;
    }
}
