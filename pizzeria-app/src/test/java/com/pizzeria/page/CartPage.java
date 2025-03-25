package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Getter
public class CartPage extends BasePage {
    private final By cartItemHeaderLocator = By.xpath("//td[@class='product-name']//a");
    private final By cartItemDeleteButtonLocator = By.xpath("//td[@class='product-remove']//a");

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
    @FindBy(css = ".woocommerce-message")
    private WebElement cartChangesMessage;
    @FindBy(css = ".checkout-button")
    private WebElement checkoutButton;
    @FindBy(xpath = "//button[@name='update_cart']")
    private WebElement updateCartButton;
    @FindBy(css = ".woocommerce form")
    private WebElement cartForm;
    @FindBy(css = "p.woocommerce-info")
    private WebElement cartInfo;
    @FindBy(css = ".woocommerce-message a")
    private WebElement restoreProduct;

    public String getTotalCartPrice() {
        return totalPrice.getText();
    }

    public CartPage deleteProductInCartByName(String productName) {
        findProductInCart(productName).findElement(cartItemDeleteButtonLocator).click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.domAttributeToBe(cartForm, "class", "woocommerce-cart-form"),
                ExpectedConditions.visibilityOf(cartInfo)
        ));
        return this;
    }

    public CartPage restoreProduct() {
        restoreProduct.click();
        wait.until(ExpectedConditions.domAttributeToBe(cartForm, "class", "woocommerce-cart-form"));
        return this;
    }

    public CartPage setCouponCode(String coupon) {
        couponCodePlaceholder.sendKeys(coupon);
        return this;
    }

    public CartPage applyCoupon() {
        applyCouponButton.click();
        wait.until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(discountAmount),
                        ExpectedConditions.visibilityOf(couponErrorMessage)
                )
        );
        return this;
    }

    private WebElement findProductInCart(String productName) {
        return cartItemsList.stream()
                .filter(product -> productName.equals(product.findElement(cartItemHeaderLocator).getText()))
                .findFirst()
                .orElseThrow();
    }

    public int getCartItemSize() {
        refreshCartItemsList();
        return cartItemsList.size();
    }

    public String getFirstItemQuantity() {
        return firstCartItemQuantity.getDomProperty("value");
    }

    public CartPage setFirstItemQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOf(firstCartItemName));
        firstCartItemQuantity.clear();
        firstCartItemQuantity.sendKeys(quantity);
        return this;
    }

    public CartPage updateCart() {
        updateCartButton.click();
        wait.until(ExpectedConditions.domAttributeToBe(cartForm, "class", "woocommerce-cart-form"));
        return this;
    }

    public String getCartMessage() {
        return cartChangesMessage.getText();
    }

    public String getCartInfo() {
        return cartInfo.getText();
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
