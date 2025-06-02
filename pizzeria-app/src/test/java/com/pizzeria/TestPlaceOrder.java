package com.pizzeria;

import com.pizzeria.page.AccountInfoPage;
import com.pizzeria.page.MainPage;
import com.pizzeria.page.OrderReceivedPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pizzeria.config.ConfigProvider.PASSWORD;
import static com.pizzeria.config.ConfigProvider.USERNAME;
import static com.pizzeria.helpers.DateFormatter.getNextDateFormatWithoutDots;
import static com.pizzeria.util.TestOrderReceivedValidation.checkOrder;
import static com.pizzeria.util.TestValues.CATEGORY_DESERTS_UPPERCASE;
import static com.pizzeria.util.TestValues.DEFAULT_USER;
import static com.pizzeria.util.TestValues.DESERT_TITLE_MENU;
import static com.pizzeria.util.TestValues.PAYMENT_METHOD_BY_CACHE;
import static com.pizzeria.util.TestValues.PAYMENT_METHOD_BY_CARD;

@DisplayName("Оформление заказа")
public class TestPlaceOrder extends BaseTest {

    private AccountInfoPage login() {
        return new MainPage()
                .getHeader()
                .openLoginPage()
                .setUsernameField(USERNAME)
                .setPasswordField(PASSWORD)
                .login();
    }

    @Test
    @DisplayName("Успешной оформление заказа с оплатой наличными")
    public void successPlacingAnOrder() {
        OrderReceivedPage page = login()
                .getHeader()
                .openMenuList()
                .openMenuPage(CATEGORY_DESERTS_UPPERCASE)
                .addToCartProduct(DESERT_TITLE_MENU)
                .openCartPageAfterAddingProductToCart(DESERT_TITLE_MENU)
                .proceedToCheckout()
                .setOrderDetails(DEFAULT_USER)
                .acceptTerms()
                .setPaymentTypeByCash()
                .submitCheckout();
        checkOrder(page, DEFAULT_USER, PAYMENT_METHOD_BY_CACHE);
    }

    @Test
    @DisplayName("Успешной оформление с оплатой картой и установкой даты заказа")
    public void successPlacingAnOrderWithDate() {
        OrderReceivedPage page = login()
                .getHeader()
                .openMenuList()
                .openMenuPage(CATEGORY_DESERTS_UPPERCASE)
                .addToCartProduct(DESERT_TITLE_MENU)
                .openCartPageAfterAddingProductToCart(DESERT_TITLE_MENU)
                .proceedToCheckout()
                .setOrderDetails(DEFAULT_USER)
                .setDeliveryDate(getNextDateFormatWithoutDots())
                .acceptTerms()
                .setPaymentTpeByCard()
                .submitCheckout();
        checkOrder(page, DEFAULT_USER, PAYMENT_METHOD_BY_CARD);
    }
}
