package com.name.parrot.page.frame;

import com.name.parrot.core.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class MainPageFooterFrame extends BasePage<MainPageFooterFrame> {
    @FindBy(css = ".footer__contacts .socialLinks a")
    private List<WebElement> socialNetworksList;
    @FindBy(xpath = "//a[text()='https://skillbox.ru/']")
    private WebElement skillboxLink;
    @FindBy(css = ".main-content iframe")
    private WebElement mainContentFrame;

    public MainPageContentFrame switchBackToFormFrame() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainContentFrame);
        return new MainPageContentFrame();
    }

    public MainPageFooterFrame openOfficialSite() {
        skillboxLink.click();
        return this;
    }

    public MainPageFooterFrame openSocialNetwork(int index) {
        socialNetworksList.get(index).click();
        return this;
    }
}
