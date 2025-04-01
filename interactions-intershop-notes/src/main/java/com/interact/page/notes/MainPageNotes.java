package com.interact.page.notes;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static com.interact.config.ConfigProvider.URL_NOTE;

@Getter
public class MainPageNotes extends BasePageNote<MainPageNotes> {
    @FindBy(tagName = "textarea")
    private WebElement textAreaNote;
    @FindBy(css = ".baseInput__field")
    private WebElement titleNote;
    @FindBy(xpath = "//button[@class='baseButton pageCreate__baseButton']")
    private WebElement addNoteButton;
    @FindBy(css = ".vb-content")
    private WebElement notesContentWindow;
    @FindBy(xpath = "//div[@class='articlePreview pageCreate__articlePreview']")
    private List<WebElement> listOfVisibleNotes;
    private final By firstNoteLocator = By.xpath("//div[@class='articlePreview pageCreate__articlePreview'][1]");
    private final By firstNoteDeleteButton = By.xpath("//button[@class='articlePreview__button'][2]");

    public MainPageNotes() {
        driver.get(URL_NOTE);
    }

    public MainPageNotes addNewNote(String title, String info) {
        titleNote.click();
        titleNote.sendKeys(title);
        textAreaNote.click();
        textAreaNote.sendKeys(info);
        addNoteButton.click();
        wait.until(ExpectedConditions.domAttributeToBe(addNoteButton, "disabled", "true"));
        return this;
    }

    public MainPageNotes addNewNoteTimes(String title, String info, int times) {
        for (int i = 0; i < times; i++) {
            addNewNote(title, info);
        }
        return this;
    }

    public MainPageNotes deleteFirstNote() {
        actions.moveToElement(driver.findElement(firstNoteLocator))
                .moveToElement(driver.findElement(firstNoteDeleteButton))
                .pause(Duration.ofMillis(200))
                .click()
                .perform();
        return this;
    }

    public MainPageNotes scrollNotesContentWindowDown() {
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", notesContentWindow);
        return this;
    }
}
