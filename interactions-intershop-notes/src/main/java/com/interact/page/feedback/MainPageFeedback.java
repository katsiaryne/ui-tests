package com.interact.page.feedback;

import com.interact.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.interact.config.ConfigProvider.URL_FEEDBACK;

@Getter
public class MainPageFeedback extends BasePage<MainPageFeedback> {
    @FindBy(css = ".vdp-datepicker.datepicker__main")
    private WebElement calendar;
    @FindBy(xpath = "//select[@name='from']")
    private WebElement timeFromSelect;
    @FindBy(xpath = "//select[@name='to']")
    private WebElement timeToSelect;
    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneField;
    @FindBy(xpath = "//form//button")
    private WebElement submitFormButton;
    @FindBy(css = ".baseInput__tipMessage span")
    private WebElement phoneMessage;
    @FindBy(css = ".datepicker__tipMessage span")
    private WebElement dateMessage;
    @FindBy(css = ".timePicker__message span")
    private WebElement timeMessage;
    private final String selectNameFromTime = "from";
    private final String selectNameToTime = "to";
    @FindBy(xpath = "//h2[@class='h2']")
    private WebElement successfulMessage;
    @FindBy(xpath = "//div[@class='firstModul__grid']//div[1]//p")
    private WebElement dateContent;
    @FindBy(xpath = "//div[@class='firstModul__grid']//div[2]//p")
    private WebElement timeContent;
    @FindBy(css = ".page__content")
    private WebElement pageContent;

    public MainPageFeedback() {
        driver.get(URL_FEEDBACK);
    }

    public MainPageFeedback submitForm() {
        submitFormButton.click();
        //   try{
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector(".page__content"))));
        //   ExpectedConditions.elementToBeClickable(submitFormButton)
        //     ));
        //} catch (TimeoutException e){};
        return this;
    }

    public MainPageFeedback setTimeFrom(String timeFrom) {
        js.executeScript("document.getElementsByName(arguments[0])[0].style.display = 'block'", selectNameFromTime);
        Select selectFrom = new Select(timeFromSelect);
        selectFrom.selectByValue(timeFrom);
        js.executeScript("document.getElementsByName(arguments[0])[0].style.display = 'none'", selectNameFromTime);
        return this;
    }

    public WebElement getSuccessfulMessage() {
        // wait.until(ExpectedConditions.visibilityOf(successfulMessage));
        return successfulMessage;
    }

    public MainPageFeedback setTimeTo(String timeTo) {
        js.executeScript("document.getElementsByName(arguments[0])[0].style.display = 'block'", selectNameToTime);
        Select selectTo = new Select(timeToSelect);
        selectTo.selectByValue(timeTo);
        js.executeScript("document.getElementsByName(arguments[0])[0].style.display = 'none'", selectNameToTime);
        return this;
    }

    public MainPageFeedback setPhone(String phone) {
        phoneField.click();
        phoneField.sendKeys(phone);
        return this;
    }

    public MainPageFeedback setDate(String date) {
        if (date.isEmpty()) return this;
        js.executeScript("arguments[0].__vue__.setDate(arguments[1])", calendar, date);
        return this;
    }
}
