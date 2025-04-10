package com.parrots;

import com.codeborne.selenide.WebDriverRunner;
import com.parrots.page.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.parrots.util.TestConstants.EXPECTED_WINDOWS_1;
import static com.parrots.util.TestConstants.EXPECTED_WINDOWS_2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOpeningWindows extends BaseTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("Открытие социальных сетей из футера")
    @Severity(SeverityLevel.MINOR)
    public void openSocialNetworksFromFooterInNewTab(int index) {
        new MainPage()
                .switchToFooterFrame()
                .openSocialMedia(index);
        assertAll(
                () -> assertEquals(EXPECTED_WINDOWS_2, WebDriverRunner.getWebDriver().getWindowHandles().size(), "Неверное количество открытых вкладок")
        );
    }

    @Test
    @DisplayName("Открытие официального сайта из футера")
    @Description("Отрытие внешей ссылки из футера главной страницы")
    @Severity(SeverityLevel.MINOR)
    public void openOfficialSiteAndClose() {
        new MainPage()
                .switchToFooterFrame()
                .openOfficialSite();

        switchToNewWindowCloseItAndSwitchToPrevious();

        assertAll(
                () -> assertEquals(EXPECTED_WINDOWS_1, WebDriverRunner.getWebDriver().getWindowHandles().size(), "Неверное количество открытых вкладок")
        );
    }
}
