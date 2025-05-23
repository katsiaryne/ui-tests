package com.name.parrot.page;

import com.name.parrot.core.BasePage;
import com.name.parrot.page.frame.MainPageContentFrame;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.name.parrot.config.ConfigProvider.URL;

public class MainPage extends BasePage<MainPage> {
    @FindBy(css = ".main-content iframe")
    private WebElement mainContentFrame;

    public MainPage() {
        driver.get(URL);
    }

    public MainPageContentFrame switchToFormFrame() {
        driver.switchTo().frame(mainContentFrame);
        return new MainPageContentFrame();
    }
}
