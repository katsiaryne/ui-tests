package com.pizzeria;

import com.pizzeria.page.CartPage;
import com.pizzeria.page.MainPage;
import com.pizzeria.page.PizzaProductPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pizzeria.helpers.StringModifier.getPriceValue;
import static com.pizzeria.util.TestValues.CART_HEADER_START_TOTAL_PRICE;
import static com.pizzeria.util.TestValues.CART_ITEM_SIZE_1;
import static com.pizzeria.util.TestValues.CART_START_TOTAL_PRICE;
import static com.pizzeria.util.TestValues.ITEM_QUANTITY_1;
import static com.pizzeria.util.TestValues.PIZZA_PRICE;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_CART;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_UPPERCASE;
import static com.pizzeria.util.TestValues.WRONG_QUANTITY_ZERO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


//https://docs.google.com/spreadsheets/d/1dNcWpkqIyTGLuzRo8HVgDAumuny0wBLsfLXLPaBG0FY/edit?gid=0#gid=0
@DisplayName("Тестирование добавления продукта в корзину")
public class TestAddProductToCart extends BaseTest {
    @DisplayName("Добавление продукта в корзину со страницы продукта")
    @Test
    public void addProductFromProductPage() {
        CartPage cartPage = new MainPage()
                .findPizzaInCarousel(PIZZA_TITLE_UPPERCASE)
                .clickFirstVisiblePizzaImage()
                .addProductToCart()
                .getHeader()
                .openCartPage(CART_HEADER_START_TOTAL_PRICE);

        assertAll(
                "Проверка содержимого корзины после добавления со страницы продукта",
                () -> assertEquals(PIZZA_PRICE, getPriceValue(cartPage.getTotalCartPriceFromHeader()), "Неверная сумма коризны в заголовке страницы"),
                () -> assertEquals(PIZZA_PRICE, getPriceValue(cartPage.getTotalCartPrice()), "Неверная сумма корзины на страницу Корзина"),
                () -> assertEquals(CART_ITEM_SIZE_1, cartPage.getCartItemSize(), "Неверное число уникальных эелементов в корзине"),
                () -> assertEquals(PIZZA_TITLE_CART, cartPage.getFirstCartItemName().getText(), "Неверный продукт в корзине"),
                () -> assertEquals(ITEM_QUANTITY_1, cartPage.getFirstItemQuantity(), "Неверное колиство продукта в корзине")
        );
    }

    @DisplayName("Добавление продукта в корзину с главной страницы")
    @Test
    public void addProductFromMainPage() {
        CartPage cartPage = new MainPage()
                .findPizzaInCarousel(PIZZA_TITLE_UPPERCASE)
                .clickAddToCartFirstVisiblePizza()
                .clickCartButtonOnFirstVisiblePizza();
        assertAll(
                "Проверка содержимого корзины после добавления с главной страницы",
                () -> assertEquals(PIZZA_PRICE, getPriceValue(cartPage.getTotalCartPriceFromHeader()), "Неверная сумма коризны в заголовке страницы"),
                () -> assertEquals(PIZZA_PRICE, getPriceValue(cartPage.getTotalCartPrice()), "Неверная сумма корзины на страницу Корзина"),
                () -> assertEquals(CART_ITEM_SIZE_1, cartPage.getCartItemSize(), "Неверное число уникальных эелементов в корзине"),
                () -> assertEquals(PIZZA_TITLE_CART, cartPage.getFirstCartItemName().getText(), "Неверный продукт в корзине"),
                () -> assertEquals(ITEM_QUANTITY_1, cartPage.getFirstItemQuantity(), "Неверное колиство продукта в корзине")
        );
    }

    @DisplayName("Добавление продукта с некорректным количеством (0)")
    @Test
    public void addProductWithWrongQuantity() {
        PizzaProductPage page = new MainPage()
                .findPizzaInCarousel(PIZZA_TITLE_UPPERCASE)
                .clickFirstVisiblePizzaImage()
                .changeQuantity(WRONG_QUANTITY_ZERO)
                .clickAddToCartButton();
        assertAll(
                "Проверка корзины при добавлении с нулевым количеством",
                () -> assertEquals(CART_START_TOTAL_PRICE, page.getHeader().getCartTotal(), "Сумма корзины изменилась, хотя количество было 0")
        );
    }
}
