package com.pizzeria;

import com.pizzeria.page.CartPage;
import com.pizzeria.page.MainPage;
import com.pizzeria.page.PizzaProductPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.pizzeria.util.TestCartValidation.checkCart;
import static com.pizzeria.util.TestValues.CART_HEADER_START_TOTAL_PRICE;
import static com.pizzeria.util.TestValues.CART_ITEM_SIZE_1;
import static com.pizzeria.util.TestValues.CART_START_TOTAL_PRICE;
import static com.pizzeria.util.TestValues.ITEM_QUANTITY_1;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_UPPERCASE;
import static com.pizzeria.util.TestValues.WRONG_QUANTITY_ZERO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


//https://docs.google.com/spreadsheets/d/1dNcWpkqIyTGLuzRo8HVgDAumuny0wBLsfLXLPaBG0FY/edit?gid=0#gid=0
@DisplayName("Тестирование добавления продукта в корзину")
public class TestAddProductToCart extends BaseTest {
    @DisplayName("Добавление продукта в корзину со страницы продукта")
    @ParameterizedTest
    @CsvSource(value = {
            "'ПИЦЦА «РАЙ»', 'Пицца \"Рай\"', '515,00'",
            "'ПИЦЦА «4 В 1»', 'Пицца \"4 в 1\"', '435,00'",
            "'ПИЦЦА «ПЕППЕРОНИ»', 'Пицца \"Пепперони\"', '485,00'"
    })
    public void addProductFromProductPage(String productNameUppercase, String productName, String productPrice) {
        CartPage cartPage = new MainPage()
                .findPizzaInCarousel(productNameUppercase)
                .clickFirstVisiblePizzaImage()
                .addProductToCart()
                .getHeader()
                .openCartPage(CART_HEADER_START_TOTAL_PRICE);

        checkCart(cartPage, productPrice, CART_ITEM_SIZE_1, productName, ITEM_QUANTITY_1);
    }

    @DisplayName("Добавление продукта в корзину с главной страницы")
    @ParameterizedTest
    @CsvSource(value = {
            "'ПИЦЦА «ВЕТЧИНА И ГРИБЫ»', 'Пицца \"Ветчина и грибы\"', '450,00'",
            "'ПИЦЦА «КАК У БАБУШКИ»', 'Пицца \"Как у бабушки\"', '480,00'"
    })
    public void addProductFromMainPage(String productNameUppercase, String productName, String productPrice) {
        CartPage cartPage = new MainPage()
                .findPizzaInCarousel(productNameUppercase)
                .clickAddToCartFirstVisiblePizza()
                .clickCartButtonOnFirstVisiblePizza();
        checkCart(cartPage, productPrice, CART_ITEM_SIZE_1, productName, ITEM_QUANTITY_1);
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
