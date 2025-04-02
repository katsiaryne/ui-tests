package com.pizzeria;

import com.pizzeria.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.asserts.SoftAssert;

import static com.pizzeria.util.TabActionUtils.closeActiveTabAndSwitchToTab;
import static com.pizzeria.util.TabActionUtils.switchToTab;

@DisplayName("Тестирование управления окном и вкладками")
public class TestWindowManipulation extends BaseTest {

    @ParameterizedTest(name = "Открытие ссылок на социальные сети  в новой вкладке")
    @CsvSource(value = {
            "Instagram, https://www.instagram.com/skillbox.ru/",
            "ВКонтакте, https://vk.com/skillbox",
            "Facebook, https://www.facebook.com/skillboxru"
    })
    public void openSocialMediaInNewTab(String socialMediaName, String expectedUrl) {
        SoftAssert softAssert = new SoftAssert();
        new MainPage().openSocialMediaInNewTab(socialMediaName);

        switchToTab(webDriver, 1);
        softAssert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Неверный адрес вкладки");
        softAssert.assertEquals(webDriver.getWindowHandles().toArray().length, 2, "Количество вкладок не совпадает с ожидаемым");

        closeActiveTabAndSwitchToTab(webDriver, 0);
        softAssert.assertEquals(webDriver.getWindowHandles().toArray().length, 1, "Количество вкладок не совпадает с ожидаемым");
        softAssert.assertAll("Проверка открытия социальной сети в новой вкладке и закрытие вкладки");
    }

    @Test
    @DisplayName("Отображение и прокрутка сайта с помощью ссылки-стрелочки")
    public void scrollWindowWIthButton() {
        SoftAssert softAssert = new SoftAssert();
        MainPage page = new MainPage().scrollPageDown();

        softAssert.assertTrue(page.getToTopButton().isDisplayed(), "Ссылка-стрелочка не отображается на экране");
        page.scrollPageUpWithToTopButton();

        softAssert.assertFalse(page.getToTopButton().isDisplayed(), "Ссылка-стрелочка отображается на экране после прокрутки");
        softAssert.assertTrue(page.getHeader().getLoginButton().isDisplayed(), "Прокрутка страницы не произошла");
        softAssert.assertAll("Проверка прокуртки окна с помощью ссылки-стрелочки");
    }
}
