package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.pizzeria.config.ConfigProvider.URL;

@Getter
public class MainPage extends BasePage<MainPage> {
    @FindBy(css = "#product1 .slick-track .slick-active")
    private List<WebElement> visiblePizzaItemsCarousel;
    @FindBy(css = "#product1 .slick-active h3")
    private WebElement firstPizzaItemInCarousel;
    @FindBy(css = "#product1 .slick-active  .add_to_cart_button")
    private WebElement addToCartButtonOfFirstPizzaInCarousel;
    @FindBy(css = "#product1 .slick-active  .added_to_cart")
    private WebElement openCartButtonOnFirstPizzaInCarousel;
    @FindBy(css = "#product1 .slick-track li")
    private List<WebElement> pizzaItemsCarousel;
    @FindBy(xpath = "//section[@id='product1']//a[@class='slick-prev']")
    private WebElement carouselPizzaPrevButton;
    @FindBy(xpath = "//section[@id='product1']//a[@class='slick-next']")
    private WebElement carouselPizzaNextButton;
    @FindBy(css = "#product2 .slick-track .slick-active")
    private List<WebElement> visibleDesertsItemsCarousel;
    @FindBy(css = "#product2 .slick-active .add_to_cart_button")
    private WebElement firstDesertButtonToCart;
    @FindBy(css = "#product2 .slick-active .added_to_cart")
    private WebElement firstDesertButtonAboutCart;
    @FindBy(xpath = "//section[@id='product2']//a[@class='slick-prev']")
    private WebElement carouselDesertsPrevButton;
    @FindBy(xpath = "//section[@id='product2']//a[@class='slick-next']")
    private WebElement carouselDesertsNextButton;
    @FindBy(css = "#accesspress_store_product-7 .slick-active")
    private List<WebElement> visibleDrinksItemsCarousel;
    @FindBy(xpath = "//aside[@id='accesspress_store_product-7']//a[@class='slick-prev']")
    private WebElement carouselDrinksPrevButton;
    @FindBy(xpath = "//aside[@id='accesspress_store_product-7']//a[@class='slick-next']")
    private WebElement carouselDrinksNextButton;

    public MainPage() {
        driver.get(URL);
    }

    public MainPage findPizzaInCarousel(String name) {
        while (!name.equals(getFirstVisiblePizzaName())) {
            actions.moveToElement(carouselPizzaNextButton).perform();
            carouselPizzaNextButton.click();
        }
        return this;
    }

    public PizzaProductPage clickFirstVisiblePizzaImage() {
        wait.until(ExpectedConditions.visibilityOf(firstPizzaItemInCarousel)).click();
        return new PizzaProductPage();
    }

    public MainPage clickAddToCartFirstVisiblePizza() {
        actions.moveToElement(addToCartButtonOfFirstPizzaInCarousel).perform();
        addToCartButtonOfFirstPizzaInCarousel.click();
        return this;
    }

    public CartPage clickCartButtonOnFirstVisiblePizza() {
        actions.moveToElement(openCartButtonOnFirstPizzaInCarousel).perform();
        openCartButtonOnFirstPizzaInCarousel.click();
        return new CartPage();
    }

    private String getFirstVisiblePizzaName() {
        return firstPizzaItemInCarousel.getText();
    }
}
