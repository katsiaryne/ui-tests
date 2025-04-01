package com.interact.page.intershop;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CatalogPage extends BasePageShop<CatalogPage> {
    @FindBy(xpath = "//h1[@class='entry-title ak-container']")
    private WebElement pageTitle;
}
