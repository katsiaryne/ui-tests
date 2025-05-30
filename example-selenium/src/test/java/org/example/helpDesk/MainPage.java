package org.example.helpDesk;

import org.example.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.config.ConfigProvider.URL;

public class MainPage extends BaseSeleniumPage {
    @FindBy(id = "id_queue")
    private WebElement queueList;
    @FindBy(xpath = "//select[@id='id_queue']//option[@value='1']")
    private WebElement queueValue;
    @FindBy(id = "id_title")
    private WebElement title;
    @FindBy(id = "id_body")
    private WebElement body;
    @FindBy(id = "id_priority")
    private WebElement priorityList;
    @FindBy(xpath = "//select[@id='id_priority']//option[@value='3']")
    private WebElement priorityValue;
    @FindBy(id = "id_due_date")
    private WebElement dueDate;
    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']//a[text()='23']")
    private WebElement dateValue;
    @FindBy(id = "id_submitter_email")
    private WebElement email;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(id = "userDropdown")
    private WebElement loginButton;

    public MainPage() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage createTicket(String titleValue, String bodyValue, String emailValue){
        queueList.click();
        queueValue.click();
        title.sendKeys(titleValue);
        body.sendKeys(bodyValue);
        priorityList.click();
        priorityValue.click();
        dueDate.click();
        dateValue.click();
        email.sendKeys(emailValue);
        submitButton.click();
        return this;
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }
}
