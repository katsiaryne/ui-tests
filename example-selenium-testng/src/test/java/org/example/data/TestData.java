package org.example.data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "nameData")
    public static Object[][] provideNameData() {
        return new Object[][]{
                {"", "Привет, !"},
                {"Name", "Привет, Name!"},
        };
    }

    @DataProvider(name = "sizeSuccessData")
    public static Object[][] provideSizeSuccessData() {
        return new Object[][]{
                {"34"},
                {"44"},
                {"39"}
        };
    }

    @DataProvider(name = "sizeErrorData")
    public static Object[][] provideSizeErrorData() {
        return new Object[][]{
                {"33"},
                {"45"},
                {"-34"}
        };
    }
}
