package com.pizzeria.util;

import org.openqa.selenium.WebDriver;

public abstract class TabActionUtils {
    public static void switchToTab(WebDriver webDriver, int tab) {
        Object[] windowHandles = webDriver.getWindowHandles().toArray();
        webDriver.switchTo().window((String) windowHandles[tab]);
    }

    public static void closeActiveTabAndSwitchToTab(WebDriver webDriver, int tab) {
        webDriver.close();
        Object[] windowHandles = webDriver.getWindowHandles().toArray();
        webDriver.switchTo().window((String) windowHandles[tab]);
    }
}
