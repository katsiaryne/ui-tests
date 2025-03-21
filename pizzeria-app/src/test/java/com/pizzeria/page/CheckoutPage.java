package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CheckoutPage extends BasePage {
    @FindBy(css = ".cart-subtotal bdi")
    private WebElement subTotalPrice;
    @FindBy(css = ".order-total bdi")
    private WebElement totalPrice;
    @FindBy(css = ".cart-discount .amount")
    private WebElement discountAmount;
    @FindBy(xpath = "//input[@id='billing_first_name']")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[@id='billing_last_name']")
    private WebElement lastNameField;
    @FindBy(xpath = "//input[@id='billing_address_1']")
    private WebElement addressField;
    @FindBy(xpath = "//input[@id='billing_city']")
    private WebElement cityField;
    @FindBy(xpath = "//input[@id='billing_state']")
    private WebElement cityStateField;
    @FindBy(xpath = "//input[@id='billing_postcode']")
    private WebElement postcodeField;
    @FindBy(xpath = "//input[@id='order_date']")
    private WebElement orderDateCalendar;
    @FindBy(xpath = "//input[@id='payment_method_cod']")
    private WebElement paymentMethodCacheButton;
    @FindBy(xpath = "input[@id='terms']")
    private WebElement acceptTermsButton;
    @FindBy(id = "place_order")
    private WebElement submitOrderButton;

    public CheckoutPage setDeliveryDate(String day, String month, String year) {
        orderDateCalendar.click();
        orderDateCalendar.sendKeys(day, month, year);
        return this;
    }
}
