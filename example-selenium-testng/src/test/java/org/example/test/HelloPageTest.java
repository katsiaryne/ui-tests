package org.example.test;

import org.example.data.TestData;
import org.example.pages.module1.HelloPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HelloPageTest extends BaseTest {
    private HelloPage helloPage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        helloPage = new HelloPage(driver);
    }

    @Test(description = "Проверяем генерацию информационного сообщения",
            dataProviderClass = TestData.class,
            dataProvider = "nameData")
    public void checkDisplayingCorrectMessage(String name, String expectedResult) {
        String result = helloPage
                .setName(name)
                .submitName()
                .getFormMessage()
                .getText();
        Assert.assertEquals(result, expectedResult, "Ожидаемое сообщение не совпадает с фактическим");
    }
}
