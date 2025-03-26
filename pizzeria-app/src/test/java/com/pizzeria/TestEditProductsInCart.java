package com.pizzeria;

import com.pizzeria.page.CartPage;
import com.pizzeria.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import static com.pizzeria.helpers.StringModifier.getPriceValue;
import static com.pizzeria.util.TestValues.CART_ITEM_SIZE_1;
import static com.pizzeria.util.TestValues.CATEGORY_DRINKS;
import static com.pizzeria.util.TestValues.DESERT_TITLE_CART;
import static com.pizzeria.util.TestValues.DESERT_TITLE_MENU;
import static com.pizzeria.util.TestValues.DRINK_TITLE_MENU;
import static com.pizzeria.util.TestValues.ITEM_QUANTITY_1;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_CART;
import static com.pizzeria.util.TestValues.PIZZA_TITLE_UPPERCASE;
import static com.pizzeria.util.TestValues.VALID_COUPON;

//https://docs.google.com/spreadsheets/d/1dNcWpkqIyTGLuzRo8HVgDAumuny0wBLsfLXLPaBG0FY/edit?gid=0#gid=0
@DisplayName("Тестирование редактирования корзины")
public class TestEditProductsInCart extends BaseTest {

    @DisplayName("Увеличение и уменьшение продукта в корзине")
    @Test
    public void addAndReduceProductInCart() {
        SoftAssert softAssert = new SoftAssert();
        CartPage cartPage = new MainPage().findPizzaInCarousel(PIZZA_TITLE_UPPERCASE)
                .clickFirstVisiblePizzaImage()
                .openPizzaBoardList()
                .setBoard()
                .changeQuantity("2")
                .clickAddToCartButton()
                .getHeader()
                .openCartFromMenu();

        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), "1140,00", "Неверная сумма корзины");
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPriceFromHeader()), "1140,00", "Неверная сумма корзины в заголовке страницы");
        softAssert.assertEquals(cartPage.getCartItemSize(), CART_ITEM_SIZE_1, "Неверное число уникальных эелементов в корзине");
        softAssert.assertEquals(cartPage.getFirstCartItemName().getText(), PIZZA_TITLE_CART, "Неверный продукт в корзине");
        softAssert.assertEquals(cartPage.getFirstItemQuantity(), "2", "Неверное колиство продукта в корзине");

        cartPage = cartPage.setFirstItemQuantity("1").updateCart();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), "570,00", "Неверная сумма корзины после уменьшения количества продуктов");
        softAssert.assertEquals(cartPage.getFirstItemQuantity(), ITEM_QUANTITY_1, "Неверное колиство продукта в корзине");

        cartPage = cartPage.setFirstItemQuantity("10").updateCart();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), "5700,00", "Неверная сумма корзины после увеличения количества продуктов");
        softAssert.assertEquals(cartPage.getFirstItemQuantity(), "10", "Неверное колиство продукта в корзине");
        softAssert.assertAll("Проверка изменения количества продукта в корзине");
    }

    @DisplayName("Удаление и восстановление продукта в коризне")
    @Test
    public void deleteAndRestoreProductInCart() {
        SoftAssert softAssert = new SoftAssert();
        CartPage cartPage = new MainPage()
                .getHeader()
                .openMenuList()
                .openMenuPageDeserts()
                .addToCartProduct(DESERT_TITLE_MENU)
                .openCartPageAfterAddingProductToCart(DESERT_TITLE_MENU)
                .deleteProductInCartByName(DESERT_TITLE_CART);

        softAssert.assertEquals(cartPage.getCartMessage(), "“Десерт \"Булочка с корицей\"” удален. Вернуть?", "Неверное сообщения действия в корзине");
        softAssert.assertEquals(cartPage.getCartInfo(), "Корзина пуста.", "Неверное инфо сообщение корзины");

        cartPage.restoreProduct();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), "115,00", "Неверная сумма корзины");
        softAssert.assertAll("Проверка удаления и восстановления продукта в корзине");
    }

    @DisplayName("Ввод купона в корзине")
    @Test
    public void addCouponToCart() {
        SoftAssert softAssert = new SoftAssert();
        CartPage cartPage = new MainPage()
                .getHeader()
                .openMenuPage()
                .openCategory(CATEGORY_DRINKS)
                .addToCartProduct(DRINK_TITLE_MENU)
                .openCartPageAfterAddingProductToCart(DRINK_TITLE_MENU)
                .setCouponCode(VALID_COUPON)
                .applyCoupon();
        softAssert.assertEquals(getPriceValue(cartPage.getTotalCartPrice()), "270,00", "Неверное значение суммы после применения купона");
        softAssert.assertEquals(cartPage.getCartMessage(), "Coupon code applied successfully.", "Неверное сообщение о применении купона");
        softAssert.assertAll("Проверка добавления купона в корзине");
    }
}
