package com.parrots.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.parrots.page.base.BaseMainPage;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class MainPageFooterFrame extends BaseMainPage<MainPageFooterFrame> {
    private final ElementsCollection socialLinks = $$(".socialLinks a");
    private final SelenideElement officialSiteLink = $(byLinkText("https://skillbox.ru/"));

    public MainPageFooterFrame openOfficialSite() {
        officialSiteLink.should(visible).click();
        return this;
    }

    public MainPageFooterFrame openSocialMedia(int index) {
        socialLinks.get(index).click();
        return this;
    }
}
