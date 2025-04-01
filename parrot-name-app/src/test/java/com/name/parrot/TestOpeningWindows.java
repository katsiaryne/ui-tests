package com.name.parrot;

import com.name.parrot.page.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOpeningWindows extends BaseTest {
    public static final int EXPECTED_WINDOWS_1 = 1;
    public static final int EXPECTED_WINDOWS_2 = 2;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void openSocialNetworksFromFooterInNewTab(int index) {
        new MainPage()
                .switchToFormFrame()
                .switchToFooter()
                .openSocialNetwork(index);

        assertAll(
                () -> assertEquals(EXPECTED_WINDOWS_2, webDriver.getWindowHandles().size(), "Неверное количество открытых вкладок")
        );
    }

    @Test
    public void openOfficialSiteAndClose() {
        new MainPage()
                .switchToFormFrame()
                .switchToFooter()
                .openOfficialSite();
        closeLastOpenedWindowAndSwitchToPrevious();

        assertAll(
                () -> assertEquals(EXPECTED_WINDOWS_1, webDriver.getWindowHandles().size(), "Неверное количество открытых вкладок")
        );
    }
}
