package com.pizzeria.page.component;

import com.pizzeria.page.CartPage;
import com.pizzeria.page.MenuPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class Header {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@class='search-field'] ")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='login-woocommerce']//a")
    private WebElement loginButton;
    By cartButtonLocator = By.xpath("//div[@class='view-cart']//a");
    @FindBy(xpath = "//li[@id='menu-item-26']//a")
    private WebElement mainButton;
    @FindBy(xpath = "//li[@id='menu-item-389']//a")
    private WebElement menuButton;
    @FindBy(xpath = "//li[@id='menu-item-390']//a")
    private WebElement menuPizzasButton;
    @FindBy(xpath = "//li[@id='menu-item-391']//a")
    private WebElement menuDesertsButton;
    @FindBy(xpath = "//li[@id='menu-item-393']//a")
    private WebElement menuDrinksButton;
    @FindBy(xpath = "//li[@id='menu-item-381']//a")
    private WebElement deliveryButton;
    @FindBy(xpath = "//li[@id='menu-item-396']//a")
    private WebElement promoButton;
    @FindBy(xpath = "//li[@id='menu-item-380']//a")
    private WebElement aboutButton;
    @FindBy(xpath = "//li[@id='menu-item-29']//a")
    private WebElement cartMenuButton;
    @FindBy(xpath = "//li[@id='menu-item-30']//a")
    private WebElement myAccountButton;
    @FindBy(xpath = "//li[@id='menu-item-31']//a")
    private WebElement checkoutButton;
    @FindBy(xpath = "//li[@id='menu-item-363']//a")
    private WebElement bonusProgramButton;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public CartPage openCartPage(String oldValue) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);

        WebElement cartButton = fluentWait.until(driver -> {
            WebElement element = driver.findElement(cartButtonLocator);
            String text = element.getText();
            if (!text.equals(oldValue) && element.isDisplayed() && element.isEnabled()) {
                element.click();
                return element;
            }
            return null;
        });
        return new CartPage();
    }

    public CartPage openCartFromMenu() {
        cartMenuButton.click();
        return new CartPage();
    }

    public Header openMenuList() {
        actions.moveToElement(menuButton).perform();
        return this;
    }

    public MenuPage openMenuPageDeserts() {
        menuDesertsButton.click();
        return new MenuPage();
    }

    public String getCartTotal() {
        WebElement cartButton = driver.findElement(cartButtonLocator);
        return cartButton.getText();
    }
}
