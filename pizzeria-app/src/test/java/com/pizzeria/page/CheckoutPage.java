package com.pizzeria.page;

import com.pizzeria.model.UserCheckoutDetails;
import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CheckoutPage extends BasePage<CheckoutPage> {
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
    @FindBy(xpath = "//input[@id='payment_method_bacs']")
    private WebElement paymentMethodCardButton;
    @FindBy(id = "terms")
    private WebElement acceptTermsButton;
    @FindBy(id = "place_order")
    private WebElement submitOrderButton;
    @FindBy(xpath = "//span[@id='select2-billing_country-container']")
    private WebElement countrySelector;
    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement searchCountry;
    @FindBy(id = "billing_phone")
    private WebElement phoneField;
    @FindBy(id = "billing_email")
    private WebElement emailField;

    public CheckoutPage setDeliveryDate(String date) {
        orderDateCalendar.click();
        orderDateCalendar.sendKeys(date);
        return this;
    }

    public OrderReceivedPage submitCheckout() {
        submitOrderButton.click();
        return new OrderReceivedPage();
    }

    public CheckoutPage setPaymentTypeByCash() {
        paymentMethodCacheButton.click();
        return this;
    }

    public CheckoutPage setPaymentTpeByCard() {
        paymentMethodCardButton.click();
        return this;
    }

    public CheckoutPage acceptTerms() {
        acceptTermsButton.click();
        return this;
    }

    public CheckoutPage setOrderDetails(UserCheckoutDetails details) {
        firstNameField.clear();
        firstNameField.sendKeys(details.getName());
        lastNameField.clear();
        lastNameField.sendKeys(details.getSurname());
        addressField.clear();
        addressField.sendKeys(details.getAddress());
        cityField.clear();
        cityField.sendKeys(details.getCity());
        cityStateField.clear();
        cityStateField.sendKeys(details.getRegion());
        postcodeField.clear();
        postcodeField.sendKeys(details.getPostcode());
        countrySelector.click();
        searchCountry.click();
        searchCountry.sendKeys(details.getCountry(), Keys.ENTER);
        phoneField.clear();
        phoneField.sendKeys(details.getPhone());
        emailField.clear();
        emailField.sendKeys(details.getEmail());
        return this;
    }
}
