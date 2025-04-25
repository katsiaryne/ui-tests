package com.interact.page.webinar;

import com.interact.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.interact.config.ConfigProvider.URL_WEBINAR;

@Getter
public class WebinarPage extends BasePage<WebinarPage> {
    @FindBy(css = ".filter-direction-button.filter-component__direction")
    private WebElement directionsButton;
    @FindBy(css = ".popup-directions__item a")
    private List<WebElement> directionsList;
    @FindBy(css = ".popup-directions__list .link-active")
    private WebElement activeDirection;
    @FindBy(css = ".ui-popup.popup-directions button")
    private WebElement closeButton;
    By directionsTextLocator = By.cssSelector(".filter-direction-button__text");

    public WebinarPage() {
        driver.get(URL_WEBINAR);
        wait.until(ExpectedConditions.urlToBe(URL_WEBINAR));
    }

    public WebinarPage openDirection(String name) {
        directionsList.stream()
                .filter(direction -> name.equals(direction.getText()))
                .findFirst()
                .orElseThrow()
                .click();
        wait.until(ExpectedConditions.textToBe(directionsTextLocator, name));
        return this;
    }

    public WebinarPage openDirections() {
        wait.until(ExpectedConditions.elementToBeClickable(directionsButton));
        directionsButton.click();
        return this;
    }

    public WebinarPage closeDirections() {
        closeButton.click();
        return this;
    }

    public String getActiveDirectionBackgroundColor() {
        return (String) js.executeScript("return getComputedStyle(arguments[0]).getPropertyValue('--bg-secondary-accent-color');", activeDirection);
    }
}
