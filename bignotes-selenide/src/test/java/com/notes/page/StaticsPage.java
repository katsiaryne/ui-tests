package com.notes.page;

import com.codeborne.selenide.SelenideElement;
import com.notes.page.base.BasePage;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@Getter
public class StaticsPage extends BasePage<StaticsPage> {
    private final SelenideElement pageStatisticDate = $(".pageStatistic__date");
    private final SelenideElement calendarMonthYear = $(".day__month_btn");
    private static final String calendarLocatorString = ".vdp-datepicker.baseCalendar.pageStatistic__calendar";

    public StaticsPage setDate(String day, String month, String year) {
        pageStatisticDate.shouldBe(visible);
        executeJavaScript("document.querySelector(arguments[3]).__vue__.setDate(new Date(arguments[0],arguments[1],arguments[2]))", year, month, day, calendarLocatorString);
        return this;
    }
}
