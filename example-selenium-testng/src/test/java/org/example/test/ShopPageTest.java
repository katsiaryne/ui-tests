package org.example.test;

import org.example.data.TestData;
import org.example.pages.module3.ShopPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShopPageTest extends BaseTest {
    private ShopPage shopPage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        shopPage = new ShopPage(driver);
    }

    @Test(description = "Проверяем генерацию успешного сообщения",
            dataProviderClass = TestData.class,
            dataProvider = "sizeSuccessData")
    public void checkDisplayingSuccessMessage(String size) {
        ShopPage page = shopPage.setInputField(size)
                .submitSize();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getSubmitMessage().getText(), "В нашем магазине есть обувь вашего размера", "Сообщение об успехе должно быть корректным");
        softAssert.assertEquals(page.getInputField().getCssValue("border-color"), "rgb(0, 128, 0)", "Цвет границ должен быть зелёным");
        softAssert.assertTrue(page.getSubmitMessage().isDisplayed(), "Сообщение должно быть видимым на странице");
        softAssert.assertAll();
    }

    @Test(description = "Проверяем генерацию неуспешного сообщения",
            dataProviderClass = TestData.class,
            dataProvider = "sizeErrorData")
    public void checkDisplayingErrorMessage(String size) {
        ShopPage page = shopPage.setInputField(size)
                .submitSize();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getSubmitMessage().getText(), "В нашем магазине нет обуви вашего размера", "СОобщение об ошибке должно быть коректным");
        softAssert.assertEquals(page.getInputField().getCssValue("border-color"), "rgb(255, 0, 0)", "Цвет границ должен быть красным");
        softAssert.assertTrue(page.getSubmitMessage().isDisplayed(), "Сообщение должно быть видимым на странице");
        softAssert.assertAll();
    }
}
