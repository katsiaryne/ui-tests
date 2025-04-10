package com.parrots.page.base;

import com.parrots.page.MainPage;
import com.parrots.page.MainPageContentFrame;
import com.parrots.page.MainPageFooterFrame;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BaseMainPage<T extends BaseMainPage<T>> {
    private final By frameLocator = By.tagName("iframe");

    public MainPage switchToMainFrame() {
        switchTo().defaultContent();
        return new MainPage();
    }

    public MainPageContentFrame switchToContentFrame() {
        switchTo().defaultContent().switchTo().frame($(frameLocator));
        return new MainPageContentFrame();
    }

    public MainPageFooterFrame switchToFooterFrame() {
        switchTo().defaultContent().switchTo().frame($(frameLocator)).switchTo().frame($(frameLocator));
        return new MainPageFooterFrame();
    }
}
