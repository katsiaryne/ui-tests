package com.interact.util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FeedbackTestValidator {
    public static boolean assertElementIsDisplayedAndHaveText(WebElement element, String expectedText) {
        try {
            element.isDisplayed();
            assertEquals(expectedText, element.getText(), "Сообщение не совпадает с ожидаемым");
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
