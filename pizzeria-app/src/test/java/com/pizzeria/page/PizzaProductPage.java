package com.pizzeria.page;

import com.pizzeria.page.base.BaseProductPage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class PizzaProductPage extends BaseProductPage<PizzaProductPage> {
    @FindBy(xpath = "//select[@id='board_pack']")
    private WebElement boardList;
    @FindBy(xpath = "//option[@value='55.00']")
    private WebElement boardValue;

    public PizzaProductPage openPizzaBoardList() {
        boardList.click();
        return this;
    }

    public PizzaProductPage setBoard() {
        boardValue.click();
        return this;
    }
}
