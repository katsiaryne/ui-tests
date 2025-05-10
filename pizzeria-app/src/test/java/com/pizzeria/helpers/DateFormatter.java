package com.pizzeria.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterWithoutDots = DateTimeFormatter.ofPattern("ddMMyyyy");
    public static String getCurrentDateFormat() {
        return LocalDate.now().format(formatter);
    }

    public static String getNextDateFormatWithoutDots() {
        return LocalDate.now().plusDays(1).format(formatterWithoutDots);
    }
}
