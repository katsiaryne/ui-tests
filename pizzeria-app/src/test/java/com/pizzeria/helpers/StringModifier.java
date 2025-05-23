package com.pizzeria.helpers;

public final class StringModifier {
    public static String getPriceValue(String str) {
        return str.replaceAll("[^0-9,]", "");
    }

    public static Double getDoublePriceValue(String str) {
        return Double.parseDouble(str.replaceAll("[^0-9,]", "").replace(",", "."));
    }
}
