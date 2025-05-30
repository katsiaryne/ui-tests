package org.example.helpDesk;

import lombok.Getter;
import org.example.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class TicketPage extends BaseSeleniumPage {
    @FindBy(xpath = "//h3")
    private WebElement title;
    @FindBy(xpath = "//th[text()='Submitter E-Mail']/following::td[1]")
    private WebElement email;
    @FindBy(xpath = "//td[@id='ticket-description']//p")
    private WebElement body;

    public TicketPage() {
        PageFactory.initElements(driver, this);
    }
}
