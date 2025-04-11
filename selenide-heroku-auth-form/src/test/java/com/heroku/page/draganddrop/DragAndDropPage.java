package com.heroku.page.draganddrop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

@Getter
public class DragAndDropPage {
    private final ElementsCollection columns = $$("#columns div");
    private final SelenideElement columnA = $(By.id("column-a"));
    private final SelenideElement columnB = $(By.id("column-b"));

    public List<String> getListOfColumns() {
        return columns.stream().map(SelenideElement::getText).toList();
    }

    public DragAndDropPage dragAndDrop() {
        actions().dragAndDrop(columnA, columnB).perform();
        return this;
    }
}
