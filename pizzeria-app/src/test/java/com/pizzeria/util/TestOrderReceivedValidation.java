package com.pizzeria.util;

import com.pizzeria.model.UserCheckoutDetails;
import com.pizzeria.page.OrderReceivedPage;

import static com.pizzeria.helpers.DateFormatter.getCurrentDateFormat;
import static com.pizzeria.util.TestValues.CART_START_TOTAL_PRICE;
import static com.pizzeria.util.TestValues.ORDER_STATUS_ORDER_RECEIVED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderReceivedValidation {

    public static void checkOrder(OrderReceivedPage page, UserCheckoutDetails userDetails, String paymentMethod) {
        UserCheckoutDetails actualDetails = getAddressFullInfo(page);

        assertAll(
                "Проверка сведений о заказе",
                () -> assertEquals(getCurrentDateFormat(), page.getOrderDate().getText()),
                () -> assertEquals(ORDER_STATUS_ORDER_RECEIVED, page.getOrderStatus().getText()),
                () -> assertEquals(userDetails.getEmail(), page.getOrderEmail().getText()),
                () -> assertEquals(CART_START_TOTAL_PRICE, page.getHeader().getCartTotal()),
                () -> assertEquals(paymentMethod, page.getPaymentMethod().getText()),
                () -> assertEquals(userDetails.getName(), actualDetails.getName()),
                () -> assertEquals(userDetails.getSurname(), actualDetails.getSurname()),
                () -> assertEquals(userDetails.getAddress(), actualDetails.getAddress()),
                () -> assertEquals(userDetails.getCity(), actualDetails.getCity()),
                () -> assertEquals(userDetails.getRegion(), actualDetails.getRegion()),
                () -> assertEquals(userDetails.getPostcode(), actualDetails.getPostcode()),
                () -> assertEquals(userDetails.getPhone(), actualDetails.getPhone()),
                () -> assertEquals(userDetails.getEmail(), actualDetails.getEmail())
        );
    }

    private static UserCheckoutDetails getAddressFullInfo(OrderReceivedPage page) {
        String[] info = page.getAddressFullInfo().getText().trim().split("\\s+");
        return UserCheckoutDetails.builder()
                .name(info[0])
                .surname(info[1])
                .address(info[2])
                .city(info[3])
                .region(info[4])
                .postcode(info[5])
                .phone(info[6])
                .email(info[7])
                .build();
    }
}
