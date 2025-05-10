package com.pizzeria.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCheckoutDetails {
    private final String name;
    private final String surname;
    private final String country;
    private final String address;
    private final String city;
    private final String region;
    private final String postcode;
    private final String phone;
    private final String email;
    private final String comment;
    private final String orderDate;
}
