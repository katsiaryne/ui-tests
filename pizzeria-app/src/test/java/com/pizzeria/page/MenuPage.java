package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class MenuPage extends BasePage {
    private final WebDriverWait wait;

    private final By entryTitleLocator = By.cssSelector("div h1.entry-title");

    @FindBy(css = ".orderby")
    private WebElement shopOrderList;
    @FindBy(xpath = "//option[@value='price-desc']")
    private WebElement shopOrderValueByPriceDesc;
    @FindBy(css = ".price_slider_wrapper .ui-slider-range")
    private WebElement priceSliderRange;
    @FindBy(css = ".price_slider_amount .price_label .from")
    private WebElement priceLabelRangeFrom;
    @FindBy(css = ".price_slider_amount .price_label .to")
    private WebElement priceLabelRangeTo;
    @FindBy(css = ".price_slider_amount .button")
    private WebElement priceSliderSubmitButton;
    @FindBy(css = ".wc-products li")
    private List<WebElement> productsList;
    @FindBy(css = ".price-cart bdi")
    private List<WebElement> productsPriceList;
    @FindBy(css = ".price-cart a")
    private List<WebElement> addToCartButtonsList;
    @FindBy(css = ".product-categories li")
    private List<WebElement> categoriesList;

    public MenuPage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public MenuPage addToCartProduct(String productName) {
        findProductByName(productName).findElement(By.linkText("В КОРЗИНУ")).click();
        return this;
    }

    public CartPage openCartPageAfterAddingProductToCart(String productName) {
        findProductByName(productName).findElement(By.linkText("ПОДРОБНЕЕ")).click();
        return new CartPage();
    }

    public MenuPage openCategory(String categoryName) {
        categoriesList.stream()
                .filter(category -> categoryName.equals(category.findElement(By.tagName("a")).getText()))
                .findFirst()
                .map(category -> category.findElement(By.tagName("a")))
                .orElseThrow()
                .click();

        wait.until(ExpectedConditions.textToBe(entryTitleLocator, categoryName.toUpperCase()));
        return this;
    }

    public int getProductsListSize() {
        return productsList.size();
    }

    private WebElement findProductByName(String productName) {
        return productsList.stream()
                .filter(product -> productName.equals(product.findElement(By.tagName("h3")).getText()))
                .findFirst()
                .orElseThrow();
    }
}
