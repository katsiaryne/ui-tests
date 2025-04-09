package com.school.balabol;

import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.school.balabol.config.ConfigProvider.URL;

public abstract class BaseTest {
    @BeforeEach
    public void setUp() {
        open(URL);
    }
}
