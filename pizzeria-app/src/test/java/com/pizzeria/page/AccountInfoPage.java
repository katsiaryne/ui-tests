package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import com.pizzeria.page.component.AccountPageNavigation;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AccountInfoPage extends BasePage {
    private final AccountPageNavigation navigation;

    @FindBy(css = ".woocommerce-message")
    private WebElement profileChangedSuccessfullyMessage;

    public AccountInfoPage(){
        this.navigation = new AccountPageNavigation(driver);
        PageFactory.initElements(driver, this);
    }
}
