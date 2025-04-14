package com.notes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.notes.config.ConfigProvider.URL;

public abstract class BaseTest {
    @BeforeEach
    public void setUp() {
        open(URL);
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
