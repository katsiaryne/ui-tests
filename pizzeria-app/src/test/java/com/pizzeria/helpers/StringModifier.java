package com.pizzeria.helpers;

public final class StringModifier {
    public static String getPriceValue(String str) {
        return str.replaceAll("[^0-9,]", "");
    }
}
