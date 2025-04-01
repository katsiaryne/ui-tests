package com.interact;

import com.interact.page.intershop.CatalogPage;
import com.interact.page.intershop.MainPageInterShop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.interact.util.TestConstantsShop.ELECTRONICS_MENU_TITLE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMenuNavigation extends BaseTest {
    @ParameterizedTest
    @DisplayName("Навигация по вложенному меню")
    @ValueSource(strings = {"Телефоны", "Планшеты", "Телевизоры", "Фото/видео", "Часы"})
    public void navigateToSubMenu(String subMenuTitle) {
        CatalogPage page = new MainPageInterShop()
                .moveToSubMenuOfCatalogButtonAndClick(ELECTRONICS_MENU_TITLE, subMenuTitle);
        assertAll(
                "Проверка перехода в каталог",
                () -> assertEquals(subMenuTitle.toUpperCase(), page.getPageTitle().getText(), "Текущая страница не соответствует ожидаемой")
        );
    }
}
