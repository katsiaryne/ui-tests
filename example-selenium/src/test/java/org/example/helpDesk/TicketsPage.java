package org.example.helpDesk;

import org.example.core.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketsPage extends BaseSeleniumPage {
    @FindBy(id = "search_query")
    private WebElement searchQuery;
    @FindBy(xpath = "//div[@class='tickettitle']")
    private WebElement ticket;

    public TicketsPage() {
        PageFactory.initElements(driver, this);
    }

    public TicketPage searchTicket(String str) {
        searchQuery.sendKeys(str, Keys.ENTER);
        ticket.click();
        return new TicketPage();
    }
}
