package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class CartPage extends BasePage {
    private final WebDriverWait wait;
    @FindBy(css = ".order-total bdi")
    private WebElement totalPrice;
    @FindBy(css = ".cart-subtotal bdi")
    private WebElement subTotalPrice;
    @FindBy(css = ".cart-discount .amount")
    private WebElement discountAmount;
    @FindBy(css = "tr.cart_item")
    private List<WebElement> cartItemsList;
    @FindBy(css = ".product-name a")
    private WebElement firstCartItemName;
    @FindBy(css = "tr.cart_item:first-child .quantity input")
    private WebElement firstCartItemQuantity;
    @FindBy(css = ".product-subtotal bdi")
    private WebElement firstCartItemPrice;
    @FindBy(id = "coupon_code")
    private WebElement couponCodePlaceholder;
    @FindBy(xpath = "//button[@name='apply_coupon']")
    private WebElement applyCouponButton;
    @FindBy(css = ".woocommerce-error li")
    private WebElement couponErrorMessage;
    @FindBy(css = ".woocommerce-message ")
    private WebElement couponSuccessMessage;
    @FindBy(css = ".checkout-button")
    private WebElement checkoutButton;

    public CartPage() {
        this.wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        PageFactory.initElements(driver, this);
    }

    public String getTotalCartPrice() {
        return totalPrice.getText();
    }

    public int getCartItemSize() {
        refreshCartItemsList();
        return cartItemsList.size();
    }

    public String getFirstItemQuantity() {
        return firstCartItemQuantity.getDomProperty("value");
    }

    private void refreshCartItemsList() {
        try {
            cartItemsList.get(0).isDisplayed();
        } catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
            cartItemsList = driver.findElements(By.cssSelector("tr.cart_item"));
            wait.until(ExpectedConditions.visibilityOfAllElements(cartItemsList));
        }
    }
}
