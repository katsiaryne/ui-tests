package com.heroku;

import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
