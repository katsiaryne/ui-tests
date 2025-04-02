package com.interact.page.notes;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class StatisticsPage extends BasePageNote<StatisticsPage> {
    @FindBy(css = ".pageStatistic__date")
    private WebElement currentDate;
    @FindBy(css = ".baseCalendar")
    private WebElement calendar;

    public StatisticsPage setDate(String year, String month, String day) {
        js.executeScript("arguments[0].__vue__.setDate(new Date(arguments[1], arguments[2], arguments[3], 0, 0, 0, 0))", calendar, year, month, day);
        wait.until(ExpectedConditions.visibilityOf(calendar));
        return this;
    }
}

