package com.interact;

import com.interact.page.intershop.MainPageInterShop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVisibilityOfScrollToTopButton extends BaseTest {
    @Test
    @DisplayName("Видимость элемента «Скролл к началу страницы»")
    public void visibilityOfScrollElement() {
        MainPageInterShop page = new MainPageInterShop()
                .scrollPageDownToFooter();
        assertAll(
                "Проверка видимости элемента скролла к началу страницы",
                () -> assertTrue(page.getToTopButton().isDisplayed(), "Элемент \"Скролл к началу страницы\" не отображается")
        );
    }
}
