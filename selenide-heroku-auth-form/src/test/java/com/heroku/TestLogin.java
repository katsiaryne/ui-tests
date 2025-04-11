package com.heroku;

import com.heroku.page.login.LoginPage;
import com.heroku.page.login.SecureAreaPage;
import com.heroku.provider.LoginArgumentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.heroku.config.ConfigProvider.URL;
import static com.heroku.util.TestConstants.CORRECT_PASSWORD;
import static com.heroku.util.TestConstants.CORRECT_USERNAME;
import static com.heroku.util.TestConstants.SUCCESS_LOGIN_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestLogin extends BaseTest {
    @BeforeEach
    public void setUp() {
        open(URL);
    }

    @Test
    public void testSuccessLogin() {
        SecureAreaPage page = new LoginPage()
                .setUsername(CORRECT_USERNAME)
                .setPassword(CORRECT_PASSWORD)
                .submitLoginFormSuccess();
        assertAll(
                "Проверка успешного входа",
                () -> page.getSuccessLoginMessage().shouldBe(visible, text(SUCCESS_LOGIN_MESSAGE)),
                () -> page.getLogoutButton().shouldBe(visible)
        );
    }

    @ParameterizedTest
    @ArgumentsSource(LoginArgumentProvider.class)
    public void testErrorLogin(String username, String password, String errorMessage) {
        LoginPage page = new LoginPage()
                .setUsername(username)
                .setPassword(password)
                .submitLoginFormError();
        assertAll(
                "Проверка неудачного входа",
                () -> page.getFormError().shouldBe(visible, text(errorMessage))
        );
    }
}
