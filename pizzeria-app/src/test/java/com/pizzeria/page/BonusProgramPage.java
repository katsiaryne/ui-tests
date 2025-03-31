package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class BonusProgramPage extends BasePage<BonusProgramPage> {
    @FindBy(id = "bonus_username")
    private WebElement usernameField;
    @FindBy(id = "bonus_phone")
    private WebElement phoneField;
    @FindBy(css = "#bonus_main button")
    private WebElement submitDataButton;
    @FindBy(id = "bonus_content")
    private WebElement formErrorMessages;
    @FindBy(css = "#bonus_main h3")
    private WebElement formSuccessMessage;

    public BonusProgramPage setUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public BonusProgramPage setPhone(String phone) {
        phoneField.sendKeys(phone);
        return this;
    }

    public BonusProgramPage activateBonusCard() {
        submitDataButton.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(formSuccessMessage),
                ExpectedConditions.visibilityOf(formErrorMessages)
        ));
        return this;
    }
}
