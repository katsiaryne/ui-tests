package com.pizzeria.page.component;

import com.pizzeria.page.EditAccountPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AccountPageNavigation {
    private final WebDriver driver;
    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-account a")
    private WebElement editAccountButton;

    public AccountPageNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EditAccountPage openEditAccountPage() {
        editAccountButton.click();
        return new EditAccountPage();
    }
}
