package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class MenuPage extends BasePage {
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

    public MenuPage addToCartProduct(String productName) {
        findProductByName(productName).findElement(By.linkText("В КОРЗИНУ")).click();
        return this;
    }

    public CartPage openCartPageAfterAddingProductToCart(String productName) {
        findProductByName(productName).findElement(By.linkText("ПОДРОБНЕЕ")).click();
        return new CartPage();
    }

    private WebElement findProductByName(String productName) {
        int index = 0;
        WebElement product = productsList.get(index);
        while (!productName.equals(product.findElement(By.tagName("h3")).getText())) {
            index += 1;
            product = productsList.get(index);
        }
        return product;
    }
}
