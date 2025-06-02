package com.pizzeria.page;

import com.pizzeria.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class OrderReceivedPage extends BasePage<OrderReceivedPage> {
    @FindBy(css = ".post-title")
    private WebElement orderStatus;
    @FindBy(css = ".date strong")
    private WebElement orderDate;
    @FindBy(css = ".email strong")
    private WebElement orderEmail;
    @FindBy(css = ".method strong")
    private WebElement paymentMethod;
    @FindBy(tagName = "address")
    private WebElement addressFullInfo;
}
