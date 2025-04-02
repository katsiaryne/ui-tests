package com.pizzeria.util;

import com.pizzeria.page.CartPage;
import org.testng.asserts.SoftAssert;

import static com.pizzeria.helpers.StringModifier.getPriceValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCartValidation {
    public static void checkCart(CartPage page, String expectedTotalPrice, int expectedCartItemSize, String expectedProductName, String expectedITemQuantity) {
        assertAll(
                "Проверка содержимого корзины",
                () -> assertEquals(expectedTotalPrice, getPriceValue(page.getTotalCartPriceFromHeader()), "Неверная сумма корзины в заголовке страницы"),
                () -> assertEquals(expectedTotalPrice, getPriceValue(page.getTotalCartPrice()), "Неверная сумма корзины на страницу Корзина"),
                () -> assertEquals(expectedCartItemSize, page.getCartItemSize(), "Неверное число уникальных эелементов в корзине"),
                () -> assertEquals(expectedProductName, page.getFirstCartItemName().getText(), "Неверный продукт в корзине"),
                () -> assertEquals(expectedITemQuantity, page.getFirstItemQuantity(), "Неверное колиство продукта в корзине")
        );
    }

    public static void checkCartSoftAssert(SoftAssert softAssert, CartPage page, String expectedTotalPrice, int expectedCartItemSize, String expectedProductName, String expectedITemQuantity) {
        softAssert.assertEquals(getPriceValue(page.getTotalCartPriceFromHeader()), expectedTotalPrice, "Неверная сумма корзины в заголовке страницы");
        softAssert.assertEquals(getPriceValue(page.getTotalCartPrice()), expectedTotalPrice, "Неверная сумма корзины на страницу Корзина");
        softAssert.assertEquals(page.getCartItemSize(), expectedCartItemSize, "Неверное число уникальных эелементов в корзине");
        softAssert.assertEquals(page.getFirstCartItemName().getText(), expectedProductName, "Неверный продукт в корзине");
        softAssert.assertEquals(page.getFirstItemQuantity(), expectedITemQuantity, "Неверное колиство продукта в корзине");
    }
}
