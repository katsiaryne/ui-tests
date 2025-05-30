package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import com.pizzeria.page.component.AccountPageNavigation;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AccountInfoPage extends BasePage<AccountInfoPage> {
    private final AccountPageNavigation navigation;

    @FindBy(css = ".woocommerce-message")
    private WebElement profileChangedSuccessfullyMessage;

    public AccountInfoPage(){
        this.navigation = new AccountPageNavigation(driver);
    }
}
