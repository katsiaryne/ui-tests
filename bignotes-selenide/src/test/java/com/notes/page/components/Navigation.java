package com.notes.page.components;

import com.codeborne.selenide.SelenideElement;
import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class Navigation {
    private final SelenideElement mainPageButton = $(By.xpath("//div[@class='theLayout__menuBody']//a[1]"));
    private final SelenideElement statsPageButton = $(By.xpath("//div[@class='theLayout__menuBody']//a[2]"));

    public StaticsPage navigateToStatisticsPage() {
        statsPageButton.shouldBe(clickable).click();
        return new StaticsPage();
    }

    public MainPage navigateToMainPage() {
        mainPageButton.shouldBe(clickable).click();
        return new MainPage();
    }
}
