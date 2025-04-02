package com.pizzeria;

import com.pizzeria.page.CartPage;
import com.pizzeria.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.pizzeria.helpers.StringModifier.getPriceValue;
import static com.pizzeria.util.TestCartValidation.checkCart;
import static com.pizzeria.util.TestCartValidation.checkCartSoftAssert;
import static com.pizzeria.util.TestValues.CART_ITEM_SIZE_1;
import static com.pizzeria.util.TestValues.CATEGORY_DRINKS;
import static com.pizzeria.util.TestValues.DESERT_PRICE;
import static com.pizzeria.util.TestValues.DESERT_TITLE_CART;
import static com.pizzeria.util.TestValues.DESERT_TITLE_MENU;
import static com.pizzeria.util.TestValues.EMPTY_CART_MESSAGE;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_CART;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_UPPERCASE;
import static com.pizzeria.util.TestValues.RETURN_PRODUCT_IN_CART_MESSAGE;
import static com.pizzeria.util.TestValues.SUCCESS_COUPON_MESSAGE;
import static com.pizzeria.util.TestValues.VALID_COUPON;

//https://docs.google.com/spreadsheets/d/1dNcWpkqIyTGLuzRo8HVgDAumuny0wBLsfLXLPaBG0FY/edit?gid=0#gid=0
@DisplayName("Тестирование редактирования корзины")
public class TestEditProductsInCart extends BaseTest {

    @DisplayName("Увеличение и уменьшение продукта в корзине")
    @ParameterizedTest
    @CsvSource(value = {
            "2, '1140,00', 1, '570,00', 10, '5700,00'"
    })
    public void addAndReduceProductInCart(String startQuantity, String startPrice,
                                          String middleQuantity, String middlePrice,
                                          String lastQuantity, String lastPrice) {
        CartPage cartPage = new MainPage().findPizzaInCarousel(PIZZA_TITLE_UPPERCASE)
                .clickFirstVisiblePizzaImage()
                .openPizzaBoardList()
                .setBoard()
                .changeQuantity(startQuantity)
                .clickAddToCartButton()
                .getHeader()
                .openCartFromMenu();

        checkCart(cartPage, startPrice, CART_ITEM_SIZE_1, PIZZA_TITLE_CART, startQuantity);
        cartPage = cartPage.setFirstItemQuantity(middleQuantity).updateCart();
        checkCartSoftAssert(softAssert, cartPage, middlePrice, CART_ITEM_SIZE_1, PIZZA_TITLE_CART, middleQuantity);
        cartPage = cartPage.setFirstItemQuantity(lastQuantity).updateCart();
        checkCartSoftAssert(softAssert, cartPage, lastPrice, CART_ITEM_SIZE_1, PIZZA_TITLE_CART, lastQuantity);
        softAssert.assertAll("Проверка изменения количества продукта в корзине");
    }

    @DisplayName("Удаление и восстановление продукта в коризне")
    @Test
    public void deleteAndRestoreProductInCart() {
        CartPage cartPage = new MainPage()
                .getHeader()
                .openMenuList()
                .openMenuPageDeserts()
                .addToCartProduct(DESERT_TITLE_MENU)
                .openCartPageAfterAddingProductToCart(DESERT_TITLE_MENU)
                .deleteProductInCartByName(DESERT_TITLE_CART);

        softAssert.assertEquals(cartPage.getCartMessage(), String.format(RETURN_PRODUCT_IN_CART_MESSAGE, DESERT_TITLE_CART), "Неверное сообщения действия в корзине");
        softAssert.assertEquals(cartPage.getCartInfo(), EMPTY_CART_MESSAGE, "Неверное инфо сообщение корзины");

        cartPage.restoreProduct();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), DESERT_PRICE, "Неверная сумма корзины");
        softAssert.assertAll("Проверка удаления и восстановления продукта в корзине");
    }

    @DisplayName("Ввод купона в корзине")
    @ParameterizedTest
    @CsvSource(value = {
            "'Айс латте', '270,00'",
            "'Напиток «Какао с маршмеллоу»', '270,00'",
            "'Напиток «Капуччино»', '135,00'"
    })
    public void addCouponToCart(String drinkName, String expectedTotalPrice) {
        CartPage cartPage = new MainPage()
                .getHeader()
                .openMenuPage()
                .openCategory(CATEGORY_DRINKS)
                .addToCartProduct(drinkName)
                .openCartPageAfterAddingProductToCart(drinkName)
                .setCouponCode(VALID_COUPON)
                .applyCoupon();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), expectedTotalPrice, "Неверное значение суммы после применения купона");
        softAssert.assertEquals(cartPage.getCartMessage(), SUCCESS_COUPON_MESSAGE, "Неверное сообщение о применении купона");
        softAssert.assertAll("Проверка добавления купона в корзине");
    }
}
