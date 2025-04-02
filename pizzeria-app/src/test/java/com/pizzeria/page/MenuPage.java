package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.pizzeria.helpers.StringModifier.getDoublePriceValue;
import static com.pizzeria.helpers.StringModifier.getPriceValue;

@Getter
public class MenuPage extends BasePage<MenuPage> {
    private final By entryTitleLocator = By.cssSelector("div h1.entry-title");

    @FindBy(css = ".orderby")
    private WebElement shopOrderList;
    @FindBy(css = ".orderby option")
    private WebElement shopOrderSortsList;
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
    @FindBy(xpath = "//div[@class='ui-slider-range ui-widget-header ui-corner-all']//following-sibling::*[1]")
    private WebElement leftSliderButton;
    @FindBy(xpath = "//div[@class='ui-slider-range ui-widget-header ui-corner-all']//following-sibling::*[2]")
    private WebElement rightSliderButton;

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

    public MenuPage submitPriceSlider() {
        priceSliderSubmitButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(priceSliderSubmitButton));
        return this;
    }

    public MenuPage moveLeftSlider(int expectedStartPrice) {
        leftSliderButton.click();
        moveSlider(leftSliderButton, clickCount(expectedStartPrice, priceLabelRangeFrom), Keys.RIGHT);
        return this;
    }

    public MenuPage moveRightSlider(int expectedFinishPrice) {
        rightSliderButton.click();
        moveSlider(rightSliderButton, clickCount(expectedFinishPrice, priceLabelRangeTo), Keys.LEFT);
        return this;
    }

    private int clickCount(int expectedPrice, WebElement startPrice) {
        return Math.abs((Integer.parseInt(getPriceValue(startPrice.getText())) - expectedPrice) / 10);
    }

    private void moveSlider(WebElement sliderButton, int movesCount, Keys direction) {
        if( movesCount == 0 ) return;
        CharSequence[] keys = new CharSequence[movesCount];
        Arrays.fill(keys, direction);
        sliderButton.sendKeys(keys);
    }

    public MenuPage setSorting(String sortName) {
        shopOrderList.click();
        wait.until(ExpectedConditions.elementToBeClickable(shopOrderSortsList));
        shopOrderSortsList.findElement(By.xpath(String.format("//option[text()='%s']", sortName))).click();
        return this;
    }

    public int getProductsListSize() {
        return productsList.size();
    }

    public List<Double> getProductsPriceList() {
        return productsPriceList.stream()
                .map(price -> getDoublePriceValue(price.getText()))
                .collect(Collectors.toList());
    }

    private WebElement findProductByName(String productName) {
        return productsList.stream()
                .filter(product -> productName.equals(product.findElement(By.tagName("h3")).getText()))
                .findFirst()
                .orElseThrow();
    }
}
