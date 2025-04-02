package com.pizzeria.page.base;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class BaseProductPage<T extends BaseProductPage<T>> extends BasePage<BaseProductPage<T>> {
    @FindBy(css = ".entry-summary h1 ")
    protected WebElement title;
    @FindBy(css = ".summary .price bdi")
    protected WebElement price;
    @FindBy(css = ".quantity input")
    protected WebElement quantity;
    @FindBy(xpath = "//button[@name='add-to-cart']")
    protected WebElement addToCartButton;
    @FindBy(css = ".posted_in a")
    protected WebElement productCategoryButton;

    public T addProductToCart() {
        addToCartButton.click();
        return (T) this;
    }

    public T changeQuantity(String newQuantity) {
        quantity.clear();
        quantity.sendKeys(newQuantity);
        return (T) this;
    }

    public T clickAddToCartButton() {
        addToCartButton.click();
        return (T) this;
    }
}
